#Chameleon


### PRE
JDK1.8+



## WHAT
一个Bean转换的小工具


## WHY
经常在业务里写 A -> B 这样的转换方法，每次都需要手动调用，及其的麻烦，所以写了一个小小的工具能够自动调用转换器。


## HOW
常规项目见测试 ： info.yannxia.java.chameleon.ConvertFactoryTest   
Spring见： info.yannxia.java.chameleon.SpringConvertFactoryTest   


如果需要和Spring集成，需要注册 info.yannxia.java.chameleon.SpringConvertFactoryImplLoader 类，监听ContextRefreshedEvent 事件。