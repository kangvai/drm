package com.kangvai.demo.util;

import java.io.*;
import java.util.ArrayList;

/**
 * @author kangvai
 * @date 2020/5/27 18:23
 */
public class FileUtil {
    /*将文本文件的内容读入到ArrayList<String>中
     * @param stringArray 字符串数组
     * @param filePath 文件路径
     */
    public static void readStringToArray(ArrayList<String> stringArray, String filePath)
            throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; //用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
        line = reader.readLine();
        while (line != null) {
            stringArray.add(line);
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }

    /*将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }

    /*
     * 将Hash值写入到指定文件中
     * file 文件路径+文件名
     * string hash值
     */
    public static void writeStringToFile(String file, String string) {
        FileWriter writer = null;
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            File fileTmp = new File(file);
            if (!fileTmp.exists() || fileTmp.length() == 0) {
                writer = new FileWriter(file);
                writer.write(string + "\n");
            } else {
                writer = new FileWriter(file, true);
                writer.write(string + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
