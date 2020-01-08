#!/usr/bin/python3
# -*- coding: gbk -*-

import pymysql
import hashlib
import codecs

db_host         = "localhost"
db_user         = "root"
db_password     = ""
db_databasename = "test"
tablename2do    = "proxydist"


def doProxyHash(strmsg):
    sha_1 = hashlib.sha1()
    sha_1.update(strmsg.encode("utf-8") )
    strmyhash = sha_1.hexdigest().lower()
    return strmyhash
    

def main():
    db = pymysql.connect(db_host, db_user, db_password, db_databasename)

    cur = db.cursor()
    strquery = "select * from " + tablename2do + " order by mccode_fwd"
    cur.execute(strquery)

    str2write = ""
    strother  = ""
    allrows   = cur.fetchall()
    cmdsupdate= []
    for row in allrows :
        mccode_fwd       = row[0]
        transtype        = row[1]
        name_mccode_fwd  = row[2]
        uri              = row[3]
        uri_tag          = row[4]
        uri_hash         = row[5]
        ip               = row[6]
        port             = row[7]
        
        str2hash  = "/" + uri_tag + uri
        strmyhash = doProxyHash(str2hash)
        strupdate = "update " + tablename2do + " set uri_hash='" + strmyhash + "' where mccode_fwd='" + mccode_fwd + "' and transtype='" + transtype + "'"
        cmdsupdate.append(strupdate)
    
    for strupdate in cmdsupdate:
        cur.execute(strupdate)
        db.commit()
    db.close()
    
if __name__== "__main__":
    main()




















