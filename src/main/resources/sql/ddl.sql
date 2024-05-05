

-- 主表
CREATE TABLE img_record (
	md5_name TEXT,
	original_name TEXT,
	ocr_text TEXT,
	lsup_time TEXT,
	upload_ip TEXT,
	file_path TEXT
, file_suffix TEXT);

-- 从表，用于记录更新后的图片的文字,使用图片的md5来做关联，可以有多条记录,使用md5和更新时间作为主键
CREATE TABLE img_record_update (
	md5_name TEXT,
	lsup_time TEXT,
	upload_ip TEXT,
	update_ocr_text TEXT
, file_suffix TEXT);