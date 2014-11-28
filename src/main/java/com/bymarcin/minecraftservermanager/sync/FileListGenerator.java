package com.bymarcin.minecraftservermanager.sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;
import com.google.gson.Gson;

public class FileListGenerator implements ITask{
	public FileList fileList = new FileList();
	public List<FileInfo> fileMod = new ArrayList<FileInfo>();
	public List<FileInfo> fileconfig = new ArrayList<FileInfo>();
	public Gson gson = new Gson();
	public List<String> toDownload = new ArrayList<String>();
	public List<String> toDelete = new ArrayList<String>();
	private File blacklist = new File(Config.GIT_REPO_PATH.get() + "/blacklist_client.json");
	
	public void searchFiles(String rootDir, String path, boolean recursive, List<FileInfo> list, ArrayList<String> blackList) {
		File folder = new File(rootDir , path);
		File[] listFiles = folder.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			if (!listFiles[i].isDirectory()) {
				if(!blackList.contains(listFiles[i].getName())){
					list.add(new FileInfo(listFiles[i].getName(),
						"." + listFiles[i].getParent().replace(rootDir, ""),
						testChecksum(listFiles[i].getPath())));
				}
			} else {
				if (recursive) {
					System.out.println(listFiles[i].getPath());
					searchFiles(rootDir,listFiles[i].getPath().replace(rootDir, ""), recursive,list,blackList);
				}
			}

		}
	}
	
	public String testChecksum(String file) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[1024];
			int read = 0;
			while ((read = fis.read(data)) != -1) {
				md5.update(data, 0, read);
			}
			;
			byte[] hashBytes = md5.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hashBytes.length; i++) {
				sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			String fileHash = sb.toString();
			fis.close();

			return fileHash;

		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return "";

	}

	@Override
	public boolean runTask() {
		
		ArrayList<String> blacklist = Utils.getBlackList(this.blacklist);
		searchFiles(Config.GIT_REPO_PATH.get(), "config", true, fileconfig, blacklist);
		searchFiles(Config.GIT_REPO_PATH.get(), "mods", true, fileMod, blacklist);
		
		fileList.setModList(fileMod);
		fileList.setConfigList(fileconfig);
		
		try {
			 PrintWriter zapis = new PrintWriter(Config.CLIENT_PATH.get() + "/filelist.json");
			 zapis.print(gson.toJson(fileList));
			 zapis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

}
