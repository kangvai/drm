package com.kangvai.demo.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author kangvai
 * @date 2020/5/27 18:22
 */
public class IPFSUtil {
    static IPFS ipfs = new IPFS("localhost", 5001);//ipfs的服务器地址和端口

    /*
     * filePathName:文件的上传路径+文件名，如D:/1.png
     * 返回值是文件hash的String
     */
    public static String upload(String filePathName) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePathName));
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    /*
     * filePathName: 文件存储在本地的路径
     * hash.txt:需要下载文件的hash值
     * 无返回值
     */
    public static void download(String filePathName, String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = ipfs.cat(filePointer);
        if (data != null) {
            File file = new File(filePathName);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
    }

    /*
     * 根据文件的hash值得到文件的String
     */
    public static String getFileStringByHash(String hash) throws Exception {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = ipfs.cat(filePointer);
        return new String(data);
    }
}
