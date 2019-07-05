package com.fast.dfs;

import org.csource.fastdfs.*;

public class DFSDemo {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        ClientGlobal.init("D:\\IdeaProjects\\pinyougou_root\\fastfds\\src\\main\\resources\\fdfs_client.conf");
        //创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        //连接tracker客户端
        TrackerServer trackerServer = trackerClient.getConnection();
        //声明storage服务端
        StorageServer storageServer = null;
        //创建storage客户端
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //上传文件
        String[] strings = storageClient.upload_file("D:\\IdeaProjects\\pinyougou_root\\fastfds\\src\\main\\resources\\photo4.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
