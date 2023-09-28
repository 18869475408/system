package com.ShoppingSystem_1;
import java.io.*;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class ShoppingHs extends Pay {
    public void getShoppingHs() {
        shoppingMg.ownGoods.add("可口可乐");
        System.out.println(shoppingMg.ownGoods);//遍历购物车里面的内容
        System.out.println(bought);//遍历购物历史
    }

    //读取购物历史信息
    public void readShoppingHs(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\购物历史.txt"));
            String hs;
            while ((hs = br.readLine()) != null) {
                bought.add(hs);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //把购物历史存到文本文档中
    public void writeShoppingHs(){
        try{
            //目前为续写模式
            BufferedWriter his = new BufferedWriter(new FileWriter("src\\购物历史.txt"));
            for (String i : bought) {
                his.write(i);
                his.newLine();
                his.flush();
            }
            his.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}