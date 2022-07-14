# 如何学习Stream和Java8中的增强特性

stream主要都是api，也不乏一些重要的概念，我们先明确一些基本概念，
然后配合一个小项目来加强我们对常见api的熟悉，这里的知识点超多

理解lambda和method reference都是对于函数式编程的语法支持

理解Functional Interface，熟悉常见的Functional Interface，
包括：`Supplier<T>, Consumer<T>, Predicate<T>, Function<T, R>, BinaryOperator<T>`，
这四种是我们在stream api也好，在其他库中也好都会经常用到的四种Functional Interface

如何理解这些Functional Interface: 以`BinaryOperator<T>`为例，先理解它的行为，直接看它的signature: (T, T) -> T，
就能理解*it accepts two objects of type T as parameters and return an object of type T*，这就够了

掌握stream中常用的组件，包括intermediate和terminal，熟悉常见的api：filter, map, collect, groupBy等

理解filer接受`Predicate<T>`，map接受`Function<T, R>`，reduce接受`BinaryOperator<T>`, collect接受的比较复杂，是`Collector<T, A, R>`

理解Collector<T, A, R>，这并不是一个Functional Interface，需要实现的方法有`supplier, accumulator, combiner, finisher`，
这里涉及到的Functional Interface包括：`Supplier<A>, BiConsumer<A, T>, BinaryOperator<A>, Function<A, R>`,
基本理解了这些设计，就能明白Functional Interface了
> T is the  type of collection objects 
> 
> A is the type of accumulator 
> 
> R is the type of return value


# 然后是项目时间

设计一个从xml中读取数据的DAO层

mock data

@data class

interface design 

implementations




