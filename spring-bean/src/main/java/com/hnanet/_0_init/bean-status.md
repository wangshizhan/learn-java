Bean主要经过instantiate，populate，initializeBean和registerDisposableBean4个状态，在状态流转中会调用很多spring预留的扩展接口。

![Bean 状态](../../../../resources/images/init/bean-status.png)
