::使用说明：将此文件及对应的mybatisGeneratorConfigFuel.xml，mybatisGeneratorConfigFuelBus.xml复制到mybatis-generator-core\1.3.2目录下，在该目录建立src文件夹，执行脚本即可。
@echo on
cd /d E:\work\repo\Maven\org\mybatis\generator\mybatis-generator-core\1.3.2
call java -jar mybatis-generator-core-1.3.2.jar -configfile mybatisGeneratorConfigFuel.xml -overwrite
call java -jar mybatis-generator-core-1.3.2.jar -configfile mybatisGeneratorConfigBus.xml -overwrite