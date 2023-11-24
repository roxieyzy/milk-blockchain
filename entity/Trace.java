package com.example.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Trace {

    private int traid;
    private int pid;
    private  String paccount;
    private  String ppassword;
    private  String pname;
    private  String pemail;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ptime;
    private int sid;
    private  String saccount;
    private  String spassword;
    private  String sname;
    private  String semail;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private  Date stime;
    private int tid;
    private  String taccount;
    private  String tpassword;
    private  String tname;
    private  String temail;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private  Date ttime;
    private  String onlykey;//溯源码
    private String paddress;
    private String pperson;
    private String tperson;
    private String tbeginaddress;
    private String tarriveaddress;
    private String sperson;
    private String saddress;

    public int getTraid() {
        return traid;
    }

    public void setTraid(int traid) {
        this.traid = traid;
    }

    public Date getTtime() {
        return ttime;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPaccount() {
        return paccount;
    }

    public void setPaccount(String paccount) {
        this.paccount = paccount;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }



    public String getPperson() {
        return pperson;
    }

    public void setPperson(String pperson) {
        this.pperson = pperson;
    }

    public String getTperson() {
        return tperson;
    }

    public void setTperson(String tperson) {
        this.tperson = tperson;
    }

    public String getTbeginaddress() {
        return tbeginaddress;
    }

    public void setTbeginaddress(String tbeginaddress) {
        this.tbeginaddress = tbeginaddress;
    }

    public String getTarriveaddress() {
        return tarriveaddress;
    }

    public void setTarriveaddress(String tarriveaddress) {
        this.tarriveaddress = tarriveaddress;
    }

    public String getSperson() {
        return sperson;
    }

    public void setSperson(String sperson) {
        this.sperson = sperson;
    }



    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }



    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTaccount() {
        return taccount;
    }

    public void setTaccount(String taccount) {
        this.taccount = taccount;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }



    public String getOnlykey() {
        return onlykey;
    }

    public void setOnlykey(String onlykey) {
        this.onlykey = onlykey;
    }
}
