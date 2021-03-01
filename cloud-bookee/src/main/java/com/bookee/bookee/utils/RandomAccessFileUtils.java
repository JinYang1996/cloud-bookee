package com.bookee.bookee.utils;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile类既可读取文件内容，也可以向文件里写内容，和其他输入输出流不同，程序可以直接跳到文件的任意位置进行读写数据
 * 所以如果只想访问文件的部分内容，可以使用该类
 * 可以向已存在的文件后追加内容
 *
 *   long getFilePointer(); 返回文件记录指针的当前位置
 *   void seek(long pos); 将文件记录指针定位到pos位置
 */
public class RandomAccessFileUtils {

    public static void main(String[] args) {
        File src = new File("E:\\test.txt");
        File desc = new File("E:\\test2.txt");

        long length = src.length();
        new DownLoadThread(0,src,desc,length/2).start();
        new DownLoadThread(length/2,src,desc,length/2).start();
    }

    static class DownLoadThread extends Thread{
        private long start;
        private File src;
        private File desc;
        private long total;

        /**
         *
         * @param start
         *            开始下载的位置
         * @param src
         *            要下载的文件
         * @param desc
         *            要下载的目的地
         * @param total
         *            要下载的总量
         */
        public DownLoadThread(long start, File src, File desc, long total) {
            this.start = start;
            this.src = src;
            this.desc = desc;
            this.total = total;
        }

        @Override
        public void run(){
            try {
                RandomAccessFile src = new RandomAccessFile(this.src,"rw");
                RandomAccessFile desc = new RandomAccessFile(this.desc,"rw");

                src.seek(start);
                desc.seek(start);

                //开始读写
                byte[] arr = new byte[1024];
                int len;
                long count=0;
                while((len = src.read(arr)) != -1){
                    //分三种情况
                    if(len+count>total){
                        //1.当读取的时候超出自己该线程的下载总量的时候，需要改变len
                        len = (int)(total-count);
                        desc.write(arr,0,len);
                        break;
                    }else if(len+count<total){
                        //证明还没到下载总量，直接将内容写入
                        desc.write(arr,0,len);
                        count += arr.length;
                    }else{
                        //证明当好到下载总量
                        desc.write(arr,0,len);
                        break;
                    }
                }

                src.close();
                desc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
