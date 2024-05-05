

### install

https://www.cnblogs.com/socketqiang/p/10960800.html
https://www.cnblogs.com/zhangb8042/p/10410804.html
https://blog.csdn.net/u012511246/article/details/124301589

```
#下载包
wget https://github.com/tesseract-ocr/tesseract/archive/4.0.0.tar.gz
wget http://www.leptonica.org/source/leptonica-1.77.0.tar.gz
 
 
#解压安装leptoinica
tar xf leptonica-1.77.0.tar.gz
cd leptonica-1.77.0
 ./configure
 make && make install
 yum install automake libtool -y
  
 #安装tesseract
 cd tesseract-4.0.0/
./autogen.sh
./configure
./configure --with-extra-includes=/usr/local/include --with-extra-libraries=/usr/local/include


```
