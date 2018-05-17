package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2018/2/10.
 */

public class DbTools {

    private static DbTools dbTools;
    private SQLiteDatabase db;
    private List<Message> list = new ArrayList<Message>();
    private List<Goods> Goodslist = new ArrayList<Goods>();
    private List<Remark> Remarklist = new ArrayList<Remark>();
    private List<Remark> RemarklistPostion = new ArrayList<Remark>();
    private List<Answer> Answerlist = new ArrayList<Answer>();
    private List<Goods> SellNumGoodslist = new ArrayList<Goods>();
    private List<Sells> Selllist = new ArrayList<Sells>();
    private List<Goods> UserGoodslist = new ArrayList<Goods>();
    private List<Bill> Billlist = new ArrayList<Bill>();

    private DbTools() {
    }

    private DbTools(Context context) {
        SQLHelper msg = new SQLHelper(context);
        db = msg.getWritableDatabase();
    }

    public synchronized static DbTools getInstance(Context context) {
        if (dbTools == null) {
            dbTools = new DbTools(context);
        }
        return dbTools;
    }

    /**
     * 在表remark中增加数据
     */
    public void addRemark(Remark remark) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("postion", remark.getPostion());
            values.put("username", remark.getUsername());
            values.put("date", remark.getDate());
            values.put("remark", remark.getRemark());
            db.insert("MyRemark", null, values);
        }
    }

    /**
     * 在表remark中查询数据
     */
    public List<Remark> QueryMarkList() {
        if (db != null) {
            Remarklist.clear();
            Cursor cursor = db.query("MyRemark", null, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String postion = cursor.getString(cursor.getColumnIndex("postion"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));

                Remark rs = new Remark();
                rs.setId(id);
                rs.setDate(date);
                rs.setPostion(postion);
                rs.setRemark(remark);
                rs.setUsername(username);

                Remarklist.add(rs);

            }
        }
        return Remarklist;
    }


    /**
     * 在表Sells中增加数据
     */
    public void addSells(Sells sells) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("username", sells.getUsername());
            values.put("number", sells.getNumber());
            values.put("buygoodsid", sells.getBuyGoodsId());
            values.put("time", sells.getTime());
            values.put("title", sells.getTit());
            values.put("img", sells.getImg());
            db.insert("MySell", null, values);
        }
    }


    /**
     * 在表Sells中删除buygoodsid的数据
     */
    public int DeleteSellList(int user, int id) {
        if (db != null) {
            int delete = db.delete("MySell", "buygoodsid=? and id=? ", new String[]{user + "", id + ""});
            return delete;
        }
        return 0;
    }

    /**
     * 在表Sells中查询数据
     */
    public List<Sells> QuerySells(String user) {
        if (db != null) {
            Selllist.clear();
            Cursor cursor = db.query(true, "MySell", null, "username=?", new String[]{user}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String buygoodsid = cursor.getString(cursor.getColumnIndex("buygoodsid"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String img = cursor.getString(cursor.getColumnIndex("img"));

                Sells sells = new Sells();
                sells.setId(id);
                sells.setUsername(username);
                sells.setNumber(number);
                sells.setBuyGoodsId(buygoodsid);
                sells.setTime(time);
                sells.setTit(title);
                sells.setImg(img);
                Selllist.add(sells);

            }
        }
        return Selllist;
    }


    /**
     * 在表remark中查询数据
     */
    public List<Remark> QueryMarkListFromPosotion(int postions) {
        if (db != null) {
            RemarklistPostion.clear();
            Cursor cursor = db.query("MyRemark", null, "postion=?", new String[]{"" + postions}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String postion = cursor.getString(cursor.getColumnIndex("postion"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));

                Remark rs = new Remark();
                rs.setId(id);
                rs.setDate(date);
                rs.setPostion(postion);
                rs.setRemark(remark);
                rs.setUsername(username);
                RemarklistPostion.add(rs);

            }
        }
        return RemarklistPostion;
    }

    /**
     * 在表goods中增加数据
     */
    public void addGoods(Goods goods) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("title", goods.getTitle());
            values.put("msg", goods.getMsg());
            values.put("price", goods.getPrice());
            values.put("img", goods.getImg());
            values.put("sellnum", goods.getSellnum());
            values.put("location", goods.getLocation());
            values.put("phone", goods.getPhone());
            values.put("usename", goods.getUsername());
            db.insert("MyGoods", null, values);
        }
    }

    /**
     * 修改数据
     */
    public int UpdateSellNum(String sellNum, int id) {
        int num = 0;
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("sellnum", sellNum);
            db.update("MyGoods", values, "id=?", new String[]{id + ""});
            return num;
        }

        return num;
    }


    /**
     * 查询销售额数据
     */
    public String QueryeSellNum(int ids) {
        String num = null;
        if (db != null) {
            Cursor cursor = db.query("MyGoods", null, "id=?", new String[]{ids + ""}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String sellnum = cursor.getString(cursor.getColumnIndex("sellnum"));
                num = sellnum;
                return num;
            }
        }
        return num;
    }

    /**
     * 查询销售图片
     */
    public String QueryePic(int ids) {
        String img = null;
        if (db != null) {
            Cursor cursor = db.query("MyGoods", null, "id=?", new String[]{ids + ""}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String imga = cursor.getString(cursor.getColumnIndex("img"));
                img = imga;
                return img;
            }
        }
        return img;
    }

    /**
     * 查询销售商品标题
     */
    public String QueryeSellTitle(int ids) {
        String title = null;
        if (db != null) {
            Cursor cursor = db.query("MyGoods", null, "id=?", new String[]{ids + ""}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                title = cursor.getString(cursor.getColumnIndex("title"));
                return title;
            }
        }
        return title;
    }


    /**
     * 在表goods中查询数据
     *
     * @param buyGoodsId
     */
    public List<Goods> QueryGoodsList(String buyGoodsId) {
        if (db != null) {
            SellNumGoodslist.clear();
            Cursor cursor = db.query("MyGoods", null, "id=?", new String[]{buyGoodsId}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String img = cursor.getString(cursor.getColumnIndex("img"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String sellnum = cursor.getString(cursor.getColumnIndex("sellnum"));

                Goods gs = new Goods();
                gs.setId(id);
                gs.setTitle(title);
                gs.setMsg(msg);
                gs.setPrice(price);
                gs.setImg(img);
                gs.setLocation(location);
                gs.setPhone(phone);
                gs.setSellnum(sellnum);
                SellNumGoodslist.add(gs);
            }
        }
        return SellNumGoodslist;
    }

    /**
     * 在表goods中根据用户查询数据
     */
    public List<Goods> QueryGoodsListForUser(String user) {
        if (db != null) {
            UserGoodslist.clear();
            Cursor cursor = db.query("MyGoods", null, "usename=?", new String[]{user}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String img = cursor.getString(cursor.getColumnIndex("img"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String sellnum = cursor.getString(cursor.getColumnIndex("sellnum"));

                Goods gs = new Goods();
                gs.setId(id);
                gs.setTitle(title);
                gs.setMsg(msg);
                gs.setPrice(price);
                gs.setImg(img);
                gs.setLocation(location);
                gs.setPhone(phone);
                gs.setSellnum(sellnum);
                UserGoodslist.add(gs);
            }
        }
        return UserGoodslist;
    }

    /**
     * 在表goods中删除id的数据
     */
    public int DeleteGoodsList(int user) {
        if (db != null) {
            int delete = db.delete("MyGoods", "id=?", new String[]{user + ""});
            return delete;
        }
        return 0;
    }

    /**
     * 在表goods中查询数据
     */
    public List<Goods> QueryGoodsList() {
        if (db != null) {
            Goodslist.clear();
            Cursor cursor = db.query("MyGoods", null, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String img = cursor.getString(cursor.getColumnIndex("img"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));

                String sellnum = cursor.getString(cursor.getColumnIndex("sellnum"));

                Goods gs = new Goods();
                gs.setId(id);
                gs.setTitle(title);
                gs.setMsg(msg);
                gs.setPrice(price);
                gs.setImg(img);
                gs.setLocation(location);
                gs.setPhone(phone);
                gs.setSellnum(sellnum);
                Goodslist.add(gs);
            }
        }
        return Goodslist;
    }

    /**
     * 在表goods中增加数据
     */
    public void addAnswer(Answer answer) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("answers", answer.getAnswer());
            values.put("answerId", answer.getAnswerId());
            values.put("name", answer.getName());
            db.insert("MyAnswer", null, values);
        }
    }

    /**
     * 在表Answer中查询数据
     */
    public List<Answer> QueryAnswerList() {
        if (db != null) {
            Answerlist.clear();
            Cursor cursor = db.query("MyAnswer", null, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String answerId = cursor.getString(cursor.getColumnIndex("answerId"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String answer = cursor.getString(cursor.getColumnIndex("answers"));

                Answer an = new Answer();
                an.setId(id);
                an.setAnswerId(answerId);
                an.setName(name);
                an.setAnswer(answer);

                Answerlist.add(an);
            }
        }
        return Answerlist;
    }

    /**
     * 在表Answer中查询数据
     */
    public List<Answer> QueryAnswerPostionList(int index) {
        if (db != null) {
            Answerlist.clear();
            Cursor cursor = db.query("MyAnswer", null, "answerId=?", new String[]{index + ""}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String answerId = cursor.getString(cursor.getColumnIndex("answerId"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String answer = cursor.getString(cursor.getColumnIndex("answers"));

                Answer an = new Answer();
                an.setId(id);
                an.setAnswerId(answerId);
                an.setName(name);
                an.setAnswer(answer);
                Answerlist.add(an);
            }
        }
        return Answerlist;
    }


    /**
     * 在表msg中增加数据
     */
    public void addUser(Message msg) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("phoneNum", msg.getPhoneNum());
            values.put("password", msg.getPwd());
            values.put("money", msg.getMoney());
            db.insert("USER", null, values);
        }
    }

    /**
     * 在表msg中查询数据
     */
    public List<Message> QueryUser() {
        if (db != null) {
            list.clear();
            Cursor cursor = db.query("USER", null, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String phoneNum = cursor.getString(cursor.getColumnIndex("phoneNum"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String money = cursor.getString(cursor.getColumnIndex("money"));
                Message ms = new Message();
                ms.setPhoneNum(phoneNum);
                ms.setPwd(password);
                ms.setId(id);
                ms.setMoney(money);
                list.add(ms);
            }
        }
        return list;
    }


    /**
     * 在表USER中查询money数据
     */
    public String QueryUserMoney(String Num) {
        String mon = null;
        if (db != null) {
            Cursor cursor = db.query("USER", null, "phoneNum=?", new String[]{Num}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String phoneNum = cursor.getString(cursor.getColumnIndex("phoneNum"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String money = cursor.getString(cursor.getColumnIndex("money"));
                mon = money;
                return mon;
            }
        }
        return mon;
    }


    /**
     * 在表USER中查询money数据
     */
    public int UpdateUserMoney(String Num, String money) {
        int user = 0;
        String total = QueryUserMoney(Num);
        int totals = Integer.parseInt(total);
        int moneys = Integer.parseInt(money);
        int update = totals - moneys;

        if (update >= 0) {
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("money", update);
                user = db.update("USER", values, "phoneNum=?", new String[]{Num});
                return user;
            }
        }
        return user;
    }

    /**
     * 在表USER中增添money数据
     */
    public int ADDUpdateUserMoney(String Num, String money) {
        int user = 0;
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("money", money);
            user = db.update("USER", values, "phoneNum=?", new String[]{Num});
            return user;
        }
        return user;
    }


    /**
     * 在表Bill中增money数据
     */
    public void ADDUserBillMoney(Bill bill) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("username", bill.getUsername());
            values.put("money", bill.getMoney());
            db.insert("MyBill", null, values);
        }
    }

    /**
     * 在表Bill中根据user查看money变动数据
     */
    public List QueryUserBillMoney(String user) {
        Billlist.clear();
        if (db != null) {
            Cursor cursor = db.query("MyBill", null, "username=?", new String[]{user}, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String moneys = cursor.getString(cursor.getColumnIndex("money"));

                Bill bill = new Bill();
                bill.setMoney(moneys);
                bill.setUsername(username);
                Billlist.add(bill);
            }
        }
        return Billlist;
    }

}
