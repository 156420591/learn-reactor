#!/usr/bin/python3
# -*- coding: gbk -*-

#yum install MySQL-python.x86_64

import pymysql
import hashlib
import codecs

db_host         = "localhost"
db_user         = "root"
db_password     = ""
db_databasename = "test"
tablename2do    = "proxydist"

filename_proxy  = "conf_hash.ini"
filename_ipport = "conf_ipport.ini"

def doProxyHash(strmsg):
    sha_1 = hashlib.sha1()
    sha_1.update(strmsg.encode("utf-8") )
    strmyhash = sha_1.hexdigest().lower()
    return strmyhash
    
### 商户名称 商户号
#[hash]    #对应的uri
#uri=/pm-sziump-https/dualRecord/system/pay/realNameCertification  
#
### 商户名称 商户号
#[uri_tag]    #(pm-sziump-https对应商户通讯地址)
#ip=
#port=

def writeProxyfile(filename, str2write):
    fo = codecs.open(filename, mode="w+b", encoding="gbk")
    fo.write(str2write)
    fo.close()

def main():
    db = pymysql.connect(db_host, db_user, db_password, db_databasename)

    cur = db.cursor()
    strquery = "select * from " + tablename2do + " order by mccode_fwd"
    cur.execute(strquery)

    str2write = ""
    strother  = ""
    for row in cur.fetchall() :
        mccode_fwd       = row[0]
        transtype        = row[1]
        name_mccode_fwd  = row[2]
        uri              = row[3]
        uri_tag          = row[4]
        uri_hash         = row[5]
        ip               = row[6]
        port             = row[7]
        
        str2hash = mccode_fwd + "," + transtype
        #strmyhash = doProxyHash(str2hash)
        strmyhash = uri_hash
        
        headof = "## " + name_mccode_fwd + "  " + mccode_fwd + "\r\n"
        newsection = headof
        newsection = newsection + "[" + strmyhash + "]\r\n"
        newsection = newsection + "uri = /" + uri_tag + uri + "\r\n\r\n"
        str2write  = str2write + newsection
        
        newsection = headof
        newsection = newsection + "[" + uri_tag + "]\r\n"
        newsection = newsection + "ip   = " + ip + "\r\n"
        newsection = newsection + "port = " + port + "\r\n"
        strother   = strother + newsection
    writeProxyfile(filename_proxy, str2write)
    writeProxyfile(filename_ipport, strother)
    
if __name__== "__main__":
    main()




















