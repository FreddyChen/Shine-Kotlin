# Shine-Kotlin
基于Retrofit+Kotlin协程实现的Kotlin网络请求库封装，支持GET/POST/PUT/DELETE请求、动态BaseUrl、请求头、请求/响应日志、自定义加解密器等。同时，支持自定义Parser（数据解析器），用于解决不同返回数据Model。  

## 文章链接
[Shine——更简单的Android网络请求库封装](https://juejin.cn/user/2084329776750989/posts)

## 使用方式
1. 添加依赖
* **Java**
  `implementation "io.github.freddychen:shine-java:$lastest_version"`
* **Kotlin**
  `implementation "io.github.freddychen:shine-kotlin:$lastest_version"`


*Note：最新版本可在[maven central shine](https://search.maven.org/artifact/io.github.freddychen/shine-kotlin)中找到。*

2. 初始化
   使用**Shine**前进行初始化，建议放到**Application#onCreate()**。
```
val options = ShineOptions.Builder()
        .setLogEnable(true)
        .setLogTag("FreddyChen")
        .setBaseUrl("https://api.oick.cn/")
        .setParserCls(CustomParser1::class)
        .build()
ShineKit.init(options)
```
当然，初始化不是强制的，**ShineOptions**会有对应的默认值，默认值可参考*参数及API说明#ShineOptions*

3. 使用
```
suspend fun fetchCatList(): ArrayList<Cat> {
    val options = RequestOptions.Builder()
        .setRequestMethod(RequestMethod.GET)
        .setBaseUrl("https://cat-fact.herokuapp.com/")
        .setFunction("facts/random?amount=2&animal_type=cat")
        .build()
        
    val type = object : TypeToken<ArrayList<Cat>>() {}.type
    return ShineKit.getRequestManager().request(
      options = options,
      type = type,
      parserCls = CustomParser1::class
    )
}
```
当然，**Type**及**Parser**参数传递我们可以利用**Kotlin**特性封装一个通用的请求方法，这些大家根据自己的业务情况来选择就好，下面提供一个示例：
```
/**
 * 异步请求
 */
suspend inline fun <reified T> request(
    requestMethod: RequestMethod,
    baseUrl: String = "https://api.oick.cn/",
    function: String,
    headers: ArrayMap<String, Any?>? = null,
    params: ArrayMap<String, Any?>? = null,
    contentType: String = NetworkConfig.DEFAULT_CONTENT_TYPE,
    parserCls: KClass<out IParser> = CustomParser1::class,
    cipherCls: KClass<out ICipher>? = null
 ): T {
    val optionsBuilder = RequestOptions.Builder()
        .setRequestMethod(requestMethod)
        .setBaseUrl(baseUrl)
        .setFunction(function)
        .setContentType(contentType)

    if (!headers.isNullOrEmpty()) {
        optionsBuilder.setHeaders(headers)
    }

    if (!params.isNullOrEmpty()) {
        optionsBuilder.setParams(params)
    }

    return ShineKit.getRequestManager()
        .request(optionsBuilder.build(), object : TypeToken<T>() {}.type, parserCls, cipherCls)
 }
````
这样的话，上面的请求可以简化为：
```
suspend fun fetchCatList(): ArrayList<Cat> {
    return request(
        requestMethod = RequestMethod.GET,
        baseUrl = "https://cat-fact.herokuapp.com/",
        function = "facts/random?amount=2&animal_type=cat",
    )
}
```

4. 示例
* 获取历史列表数据
  | 服务器域名 | 接口地址 | 参数 | 返回数据结构 | 备注 |
  | -- | -- | -- | -- | -- |
  | https://api.oick.cn/ | lishi/api.php | / | code、day、result | / |

例：
```
{
    "code":"1",
    "day":"01/ 17",
    "result":[
        {
            "date":"395年01月17日",
            "title":"罗马帝国分裂为西罗马帝国和东罗马帝国"
        }
    ]
}
```
调用方式：
```
suspend fun fetchHistoryList(): ArrayList<History> {
    return request(
        requestMethod = RequestMethod.POST,
        function = "lishi/api.php",
    )
}
```

* 获取新闻列表数据
  | 服务器域名 | 接口地址 | 参数 | 返回数据结构 | 备注 |
  | -- | -- | -- | -- | -- |
  | https://is.snssdk.com/ | api/news/feed/v51/ | / | message、data | / |

例：
```
{
    "message":"success",
    "data":[
        {
            "content":"test"
        }
    ]
}
```
调用方式：
```
suspend fun fetchJournalismList(): ArrayList<Journalism> {
    return request(
        requestMethod = RequestMethod.GET,
        baseUrl = "https://is.snssdk.com/",
        function = "api/news/feed/v51/",
        parserCls = CustomParser2::class,
    )
}

```
*Note：如有业务需求使用同步请求方式，只需要把`request()`方法改成`syncRequest()`方法即可*。

## 版本记录

| 版本号 | 修改时间 | 版本说明 |
| -- | -- | -- |
| 0.0.7 | 2022.01.16 | 首次提交 |
| 0.0.8 | 2022.02.15 | 修改minSdkVersion为19 |

## 免费开放的Api
提供两个免费开放Api平台给大家，方便测试：
* [红花会 / 免费的api接口](https://gitee.com/safflower_club/free_api_interface#https://gitee.com/link?target=https%3A%2F%2Fwww.free-api.com%2Fdoc%2F533)
* [public-apis](https://github.com/public-apis/public-apis)  

# License


    Copyright 2022, chenshichao

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
   
