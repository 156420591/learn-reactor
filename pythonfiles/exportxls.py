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
outputxlsfilename= "cds_hash.xlsx"
xlsSheetname     = "Sheet1"


def main():
    sqlcmds = []
    df = pd.read_excel(inputxlsfilename, xlsSheetname, dtype={'mccode': str, 'transtype':str, 'description':str, 'uri':str})
    lstmccode    = df['mccode']
    lsttranstype = df['transtype']
    lsthash = []
    db = pymysql.connect(db_host, db_user, db_password, db_databasename)
    cur = db.cursor()
    for i in range(0, len(lstmccode)):
        mccode     = lstmccode[i]
        transtype  = lsttranstype[i]
        strsql = "select uri_hash from " + tablename2do + " where mccode_fwd='" + mccode + "' and transtype='" + transtype + "'"
        cur.execute(strsql)
        uri_hash = cur.fetchone()
        uri_hash = uri_hash[0]
        lsthash.append(uri_hash)
    df['hash'] = lsthash
    writer = ExcelWriter(outputxlsfilename)
    df.to_excel(writer, xlsSheetname, index=False)
    writer.save()
    db.close()
        
    
    
if __name__== "__main__":
    main()




















