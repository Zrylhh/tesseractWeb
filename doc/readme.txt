

流程简单说明

输入：
通过前端页面上传图片，
后端接收保存图片，（图片写到硬盘-路径记录到数据库，简单点sqlite）
计算出图片的md5值，
    如果已经有，那么查数据库记录返回
    如果没有，那么保存图片，解析文字，写入到数据库
    后端通过tess4j把图片中的文字解析，保存到数据库
把文字返回前端（前端轮询）展示

前端提供一个输入框，可以由用户自己输入文字，
前端提交文字，后端接收记录到数据库




表结构设计

-- 主表
CREATE TABLE img_record (
	md5_name TEXT,
	original_name TEXT,
	ocr_text TEXT,
	lsup_time TEXT,
	upload_ip TEXT,
	file_path TEXT
, file_suffix TEXT);

-- 从表，用于记录更新后的图片的文字,使用图片的md5来做关联，可以有多条记录
CREATE TABLE img_record_update (
	md5_name TEXT,
	lsup_time TEXT,
	upload_ip TEXT,
	update_ocr_text TEXT
, file_suffix TEXT);



搜索
1. 用like，like主表里面的ocr_text字段即可
2. 用like，like主表和更新表里面的字段



通过lucene进行分词索引，后续可以通过随机关键词来检索图片


1. 部分图片会引起报错        √
2. 集成lucene对文字建立索引，方便后续全文搜索