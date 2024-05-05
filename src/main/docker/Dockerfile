FROM clearlinux/tesseract-ocr:4.1.0
RUN swupd bundle-add java-basic
LABEL PROJECT="tesseract_web"
WORKDIR /opt/app
ENV LC_ALL en_US.UTF-8
ADD docker-entrypoint.sh /opt/app/
RUN chmod +x /opt/app/docker-entrypoint.sh
ADD TesseractWeb-*.jar TesseractWeb.jar
ENTRYPOINT ["/opt/app/docker-entrypoint.sh"]