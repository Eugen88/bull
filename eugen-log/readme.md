# Spring AOP记录操作日志

项目基于spring boot1.5.13.REALEASE版本搭建，采用spring aop方式处理get、post请求提交的数据，提取数据并转义处理后，持久化到数据库中。本示例中采用h2数据库测试，建表脚本和初始化数据分别在sql/table.sql和init.sql中，项目启动时，会把运行脚本，初始化运行环境。项目启动后，可以通过http://localhost:8080/log/h2-console查看h2数据库，用户名sa，密码为空。

本工程中需要注意的问题：<br>
1. com.eugen.resp定义了返回数据类型，所有controller层处理请求后均需返回该包中定义的数据类型，便于操作日志模块中对返回结果的处理。对于一般的项目来讲，该包中定义的数据结构可以满足需要，如需要拓展，继承Response类即可。笔者认为，对一般项目而言，返回的数据结构基本上可用如下4中类型描述。<br>
&nbsp;&nbsp;&nbsp;&nbsp;1).返回处理结果<br>
    
	@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
	public class Response {
    
        /**
         * 返回状态码，定义200时成功，其它失败
         */
        private int status;
    
        /**
         * 自定义编码
         */
        private String code;
        
        /**
         * 返回描述
         */
        private String desc;
    }
<br> 
&nbsp;&nbsp;&nbsp;&nbsp;2).返回列表型数据，适用于下拉框
    
	@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
	public class ResponseList extends Response
    {

        /**
         * 数据
         */
        private List<?> list;

        public ResponseList(int status, String code, String desc, List<?> list)
        {
            super(status, code, desc);
            this.list = list;
        }
    }
<br>
&nbsp;&nbsp;&nbsp;&nbsp;3).返回表格数据，适用于表格
    
	@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
	public class ResponsePage extends Response
    {

        /**
         * 分页数据节点
         */
        private Page page;

        public ResponsePage(int status, String code, String desc, Page page)
        {
            super(status, code, desc);
            this.page = page;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class Page
        {
            /**
             * 总页数
             */
            private long totalPage;

            /**
             * 总记录数
             */
            private long totalSize;

            /**
             * 当前页数
             */
            private int currentPage;

            /**
             * 每页记录数
             */
            private int pageSize;

            /**
             * 数据
             */
            private List<?> list;
        }
    }
<br>
&nbsp;&nbsp;&nbsp;&nbsp;4).返回一个对象，适用于返回一条记录情况
    
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class ResponseObj extends Response
	{
    	private Object data;

    	public ResponseObj(int status, String code, String desc, Object data)
    	{
        	super(status, code, desc);
        	this.data = data;
    	}
	}
<br>
2.  请求中的参数需要转换成可读字符串，在资源文件application-logResources.yml中定义，如下：<br>
    
	com:
  	  eugen:
        log:
          demo:
            userName: 姓名
日志模块在运行时，会按照controller所在的包名及参数中的参数名，组成key值，查找可读字符串。如本示例中http://localhost:8080/log/demo/get?userName=张。日志模块，会按com.eugen.log.demo.userName查找到“姓名”，最后记入数据库中数据形如{"姓名": "张"}。
<br>
3.  WrapFilter的作用，是将post请求参数放入ThreadLocal中，便于获取post数据，因为post数据只能以流的形式获取一次，第二次获取时会发生错误，所以增加WrapFilter处理。