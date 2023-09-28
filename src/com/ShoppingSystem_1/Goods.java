package com.ShoppingSystem_1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class Goods {
    //商品的信息包括：商品编号、商品名称、生产厂家、生产日期、型号、进货价、零售价格、数量。所有信息都要相互关联//8要素
    ArrayList<Integer> goodsNumber = new ArrayList<>();//：商品编号
    ArrayList<String> goodsName = new ArrayList<>();//商品名称
    ArrayList<String> goodsManufacturer = new ArrayList<>();//生产厂家
    ArrayList<String> goodsDateOfProduction = new ArrayList<>();//生产日期
    ArrayList<String> goodsTypes = new ArrayList<>();//型号
    ArrayList<Double> goodsImportPrice = new ArrayList<>();//进货价
    ArrayList<Double> goodsExitPrice = new ArrayList<>();//零售价格
    ArrayList<Integer> goodsCount = new ArrayList<>();//数量
    int num = 0;

    //添加商品,现在添加的属于初始化内容主要用于测试使用；
    public void addGoods0() {
        goodsNumber.add(1);
        goodsName.add("茶π");
        goodsManufacturer.add("农夫山泉");
        goodsDateOfProduction.add("2021.7.19");
        goodsTypes.add("1886");
        goodsImportPrice.add(2.5);
        goodsExitPrice.add(3.0);
        goodsCount.add(500);
        goodsNumber.add(2);
        goodsName.add("冰红茶");
        goodsManufacturer.add("康师傅");
        goodsDateOfProduction.add("2021.8.19");
        goodsTypes.add("2003");
        goodsImportPrice.add(0.8);
        goodsExitPrice.add(1.2);
        goodsCount.add(500);
    }

    //列出所有商品（这里不列出商品的进货价，进价部分只有管理员可见）
    public void showAllGoods() {
        addGoods0();
        System.out.println("以下为全部商品信息：");
        System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
        for (int i = 0; i < goodsNumber.size(); i++) {
            Object obj1 = goodsNumber.get(i);
            Object obj2 = goodsName.get(i);
            Object obj3 = goodsManufacturer.get(i);
            Object obj4 = goodsDateOfProduction.get(i);
            Object obj5 = goodsTypes.get(i);
            Object obj6 = goodsExitPrice.get(i);
            Object obj7 = goodsCount.get(i);
            System.out.println("\t"+obj1 + "\t" + obj2 + "\t" + obj3 + "\t\t" + obj4 + "\t" + obj5 + "\t" + obj6 + "\t" + obj7 + "\t");
        }
    }
        //查询商品
        //可以根据商品名称、生产厂家、零售价格进行单独查询或者组合查询
        public  void searchGoods() {
            addGoods0();
            System.out.println("请选择您的查询方式：");
            System.out.println("\t1--商品名或生产厂家查询\n\t2--零售价查询");
            Scanner choose = new Scanner(System.in);
            int choose6 = choose.nextInt();
            switch (choose6) {
                case 1:
                    System.out.println();
                    System.out.println("*********欢迎来到商品查询界面********");
                    System.out.println("|\t1--输入的商品名或生产厂家查询     |");
                    System.out.println("|\t2--输入的商品名和生产厂家查询     |");
                    System.out.println("*********************************");
                    System.out.println("请输入您的选择：");
                    Scanner choose_ = new Scanner(System.in);
                    int che = choose_.nextInt();

                    switch (che) {
                        case 1:
                            System.out.println("请输入您要搜索商品的商品名或生产厂家：");
                            Scanner sch = new Scanner(System.in);
                            String search = sch.next();
                            if (goodsName.contains(search)) {
                                int site = goodsName.indexOf(search);//定位商品地址
                                System.out.println("商品名：" + search);
                                System.out.println("商品编号：" + goodsNumber.get(site));
                                System.out.println("生产商：" + goodsManufacturer.get(site));
                                System.out.println("生产日期：" + goodsDateOfProduction.get(site));
                                System.out.println("产品型号：" + goodsTypes.get(site));
                                System.out.println("零售价：" + goodsExitPrice.get(site));
                                System.out.println("库存量：" + goodsCount.get(site));
                            } else if (goodsManufacturer.contains(search)) {
                                int site1 = goodsManufacturer.indexOf(search);//定位商品地址
                                System.out.println("商品名：" + goodsName.get(site1));
                                System.out.println("商品编号：" + goodsNumber.get(site1));
                                System.out.println("生产商：" + search);
                                System.out.println("生产日期：" + goodsDateOfProduction.get(site1));
                                System.out.println("产品型号：" + goodsTypes.get(site1));
                                System.out.println("零售价：" + goodsExitPrice.get(site1));
                                System.out.println("库存量：" + goodsCount.get(site1));
                            } else {
                                System.out.println("暂无此商品的任何信息！");
                            }
                            break;
                        case 2://有BUG不能查询出结果
                            System.out.println("请输入您要搜索商品的商品名和生产厂家：");
                            Scanner sch1 = new Scanner(System.in);
                            String search1 = sch1.next();
                            //正则表达分割输入的字符串
                            String[] str = new String[2];
                            str = search1.split("\\s+");
                            String str1 = str[0];
                            String str2 = str[1];
                            if (goodsName.contains(str1) && goodsManufacturer.contains(str2)) {
                                int location = goodsName.indexOf(str1);//定位商品地址
                                System.out.println("商品名：" + goodsName.get(location));
                                System.out.println("商品编号：" + goodsNumber.get(location));
                                System.out.println("生产商：" + goodsManufacturer.get(location));
                                System.out.println("生产日期：" + goodsDateOfProduction.get(location));
                                System.out.println("产品型号：" + goodsTypes.get(location));
                                System.out.println("零售价：" + goodsExitPrice.get(location));
                                System.out.println("库存量：" + goodsCount.get(location));
                            } else if (goodsName.contains(str2) && goodsManufacturer.contains(str1)) {
                                int place = goodsName.indexOf(str2);//定位商品地址
                                System.out.println("商品名：" + goodsName.get(place));
                                System.out.println("商品编号：" + goodsNumber.get(place));
                                System.out.println("生产商：" + goodsManufacturer.get(place));
                                System.out.println("生产日期：" + goodsDateOfProduction.get(place));
                                System.out.println("产品型号：" + goodsTypes.get(place));
                                System.out.println("零售价：" + goodsExitPrice.get(place));
                                System.out.println("库存量：" + goodsCount.get(place));
                            } else {
                                System.out.println("暂无此商品的任何信息！");
                            }
                            break;
                    }


                    break;
                case 2:
                    System.out.println("请输入商品零售价进行查询：");
                    Scanner prc = new Scanner(System.in);
                    double price = prc.nextDouble();
                    if (goodsExitPrice.contains(price)) {
                        int address = goodsExitPrice.indexOf(price);//定位商品
                        System.out.println("商品名：" + goodsName.get(address));
                        System.out.println("商品编号：" + goodsNumber.get(address));
                        System.out.println("生产商：" + goodsManufacturer.get(address));
                        System.out.println("生产日期：" + goodsDateOfProduction.get(address));
                        System.out.println("产品型号：" + goodsTypes.get(address));
                        System.out.println("零售价：" + goodsExitPrice.get(address));
                        System.out.println("库存量：" + goodsCount.get(address));
                    } else {
                        System.out.println("暂无此商品的任何信息！");
                    }

            }
        }

        //从文本文件中获取商品信息
        //读取文本数据至集合并转换数据类型
        //获取上一次程序运行时产生的数据
        public void acquireGoodsInformation() {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src\\商品编号.txt"));
                BufferedReader br1 = new BufferedReader(new FileReader("src\\商品名称.txt"));
                BufferedReader br2 = new BufferedReader(new FileReader("src\\生产厂家.txt"));
                BufferedReader br3 = new BufferedReader(new FileReader("src\\生产日期.txt"));
                BufferedReader br4 = new BufferedReader(new FileReader("src\\型号.txt"));
                BufferedReader br5 = new BufferedReader(new FileReader("src\\进货价.txt"));
                BufferedReader br6 = new BufferedReader(new FileReader("src\\零售价格.txt"));
                BufferedReader br7 = new BufferedReader(new FileReader("src\\商品数量.txt"));

                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    goodsNumber.add(Integer.parseInt(sCurrentLine));
                }
                while ((sCurrentLine = br1.readLine()) != null) {
                    goodsName.add(sCurrentLine);
                }
                while ((sCurrentLine = br2.readLine()) != null) {
                    goodsManufacturer.add(sCurrentLine);
                }
                while ((sCurrentLine = br3.readLine()) != null) {
                    goodsDateOfProduction.add(sCurrentLine);
                }
                while ((sCurrentLine = br4.readLine()) != null) {
                    goodsTypes.add(sCurrentLine);
                }
                while ((sCurrentLine = br5.readLine()) != null) {
                    goodsImportPrice.add(Double.parseDouble(sCurrentLine));
                }
                while ((sCurrentLine = br6.readLine()) != null) {
                    goodsExitPrice.add(Double.parseDouble(sCurrentLine));
                }
                while ((sCurrentLine = br7.readLine()) != null) {
                    goodsCount.add(Integer.parseInt(sCurrentLine));
                }
                br.close();
                br1.close();
                br2.close();
                br3.close();
                br4.close();
                br5.close();
                br6.close();
                br7.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //往文本文件中写入数据
        //把数据写入文本文档
        public void writeGoods () {
            //目前为续写状态，后续根据实际需求更改
            try {
                //商品的信息包括：商品编号、商品名称、生产厂家、生产日期、型号、进货价、零售价格、数量。
                BufferedWriter number = new BufferedWriter(new FileWriter("src\\商品编号.txt"));
                BufferedWriter name = new BufferedWriter(new FileWriter("src\\商品名称.txt"));
                BufferedWriter producer = new BufferedWriter(new FileWriter("src\\生产厂家.txt"));
                BufferedWriter time = new BufferedWriter(new FileWriter("src\\生产日期.txt"));
                BufferedWriter type = new BufferedWriter(new FileWriter("src\\型号.txt"));
                BufferedWriter buy = new BufferedWriter(new FileWriter("src\\进货价.txt"));
                BufferedWriter sell = new BufferedWriter(new FileWriter("src\\零售价格.txt"));
                BufferedWriter count = new BufferedWriter(new FileWriter("src\\商品数量.txt"));

                for (int i : goodsNumber) {
                    number.write(String.valueOf(i));
                    number.newLine();
                    number.flush();
                }
                for (String i : goodsName) {
                    name.write(i);
                    name.newLine();
                    name.flush();
                }
                for (String i : goodsManufacturer) {
                    producer.write(i);
                    producer.newLine();
                    producer.flush();
                }
                for (String i : goodsDateOfProduction) {
                    time.write(i);
                    time.newLine();
                    time.flush();
                }
                for (String i : goodsTypes) {
                    type.write(i);
                    type.newLine();
                    type.flush();
                }
                for (double i : goodsImportPrice) {
                    buy.write(String.valueOf(i));
                    buy.newLine();
                    buy.flush();
                }
                for (double i : goodsExitPrice) {
                    sell.write(String.valueOf(i));
                    sell.newLine();
                    sell.flush();
                }
                for (int i : goodsCount) {
                    count.write(String.valueOf(i));
                    count.newLine();
                    count.flush();
                }

                number.close();
                name.close();
                producer.close();
                time.close();
                type.close();
                buy.close();
                sell.close();
                count.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
