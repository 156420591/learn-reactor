#!/usr/bin/python3
# -*- coding: gbk -*-

import pymysql
import hashlib
import codecs
import pandas as pd
from pandas import ExcelWriter
from pandas import ExcelFile
from urllib.parse import urlparse

db_host         = "localhost"
db_user         = "root"
db_password     = ""
db_databasename = "test"
tablename2do    = "proxydist"
inputxlsfilename= "test_pm_http.xlsx"
xlsSheetname    = "Sheet1"


def main():
    sqlcmds = []
    df = pd.read_excel(inputxlsfilename, xlsSheetname, dtype={'mccode': str, 'transtype':str, 'description':str, 'uri':str})
    for index, row in df.iterrows():
        mccode       = row["mccode"]
        transtype    = row["transtype"]
        description  = row["description"]
        uri          = row["uri"]
        ourl = urlparse(uri)
        #print(ourl)
        #print(ourl.hostname)
        #print(ourl.port)
        #print(ourl.path)
        sqlitem = "insert into " + tablename2do + "(mccode_fwd, transtype, name_mccode_fwd, uri, ip, port)values('" + mccode +"','" + transtype + "','" + description + "','" + ourl.path + "','" + ourl.hostname + "','" + str(ourl.port) +"') ON DUPLICATE KEY UPDATE name_mccode_fwd='"  + description +"', uri='" + ourl.path + "', ip='" + ourl.hostname + "', port='" + str(ourl.port) + "';"
        if ourl.hostname.find("proxy") != -1:
            sqlitem = "insert into " + tablename2do + "(mccode_fwd, transtype, name_mccode_fwd, uri, old_proxy_port)values('" + mccode +"','" + transtype + "','" + description + "','" + ourl.path +"','" + str(ourl.port) + "') ON DUPLICATE KEY UPDATE name_mccode_fwd='"  + description +"', uri='" + ourl.path + "';"
        print(sqlitem)
        sqlcmds.append(sqlitem)
        
    
    db = pymysql.connect(db_host, db_user, db_password, db_databasename)
    cur = db.cursor()
    for row in sqlcmds :
        cur.execute(row)
        db.commit()
        
    db.close()
    
if __name__== "__main__":
    main()




















