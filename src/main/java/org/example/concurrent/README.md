# 如何学习并发

理解并发和线程同步是两个问题，线程同步主要是解决操作非原子性造成的数据不一致问题的，并发是为了实现高并发，多线程

理解java中最基本的执行单元概念：`Callable<T>, Runnable`以及他们的区别

理解`Future<T>`的作用，是用来管控线程执行的句柄

理解线程池的作用，线程池接口ExecutorService，以及对应的实现类FixedThreadPool, CachedThreadPool等

理解submit和execute的区别

理解Comp了tableFuture其实就是JavaScript中的Promise，能利用CompletableFuture完成串行调用和并行调用

理解ForkJoin是Java提供的并行计算框架，能利用ForkJoin完成对单线程算法的优化，比如：mergeSort, Fibnacci

理解`RecursiveTask<T>, RecursiveAction`，以及他们的区别


