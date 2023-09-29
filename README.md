# system
源代码在master分支下
版本1.0：使用ArrayList存储用户信息
版本2.0：使用MySQL存储用户信息
版本3.0：在版本2.0的基础上图形化界面

系统模块详细设计
(1) 用户注册
用户注册是用户想要购买商品所必须进行的，用户注册需要填写正确的信息，用户账户一旦注册就不能修改，用户的其他信息可以修改。用户填写信息需要正确填写各项信息，如收货地址，用户下订单后用户的收货地址如果是错误的则无法正确发货的。用户注册后，使用用户名和密码登录系统，可以进行购物、下订单、提交留言操作。
(2) 用户登录
用户登录时需要输入用户名和密码，系统对用户输入信息进行验证，如果用户输入信息错误则需要重新输入，用户输入正确则可以成功登录，跳转到首页，显示用户常用操作例如修改密码，修改信息，退出等。用户如果忘记密码可以通过查找密码来重新设置密码
(3) 用户信息修改
用户登录后可以修改个人信息，修改个人信息后需要重新登录才能查看到更新之后的信息。用户登录后可以修改密码，重新登录需要使用修改后的密码才能登录。
(4) 用户找回密码
用户如果忘记密码，可以通过输入注册时的信息来重设密码。如果输入的注册信息错误则无法重设密码，只有输入正确的注册信息才能进行密码重设。
(5) 用户查看商品
用户可以通过搜索商品名称来搜索商品。
(6) 添加到购物车
用户登录后可以把商品添加到购物车，也可以对购物车商品进行删除。如果商品的剩余数量为0则无法把商品添加到购物车。
 (8) 管理商品
管理员登录后台后，可以对商品进行管理，添加、删除商品。添加时如果商品已经存在则无法添加。
(9) 系统管理
管理员登录后，可以修改登录密码、退出系统。
