# ConsoleFilter
Bukkit插件，控制台输出过滤器

# 支持版本
按理来说，支持1.7及以后的所有版本

# 命令
* `consolefilter reload` - 重载配置，并加载配置中的过滤器
* `consolefilter info <log>...` - 在控制台以INFO等级输出一段日志
* `consolefilter severe <log>...` - 在控制台以ERROR等级输出一段日志
* `consolefilter warning <log>...` - 在控制台以WARN等级输出一段日志
* `consolefilter fine <log>...` - 在控制台以FINE等级输出一段日志
* `consolefilter finer <log>...` - 在控制台以FINER等级输出一段日志
* `consolefilter finest <log>...` - 在控制台以FINEST等级输出一段日志
* `consolefilter config <log>...` - 在控制台以CONFIG等级输出一段日志

其中
* `<>`尖括号内的是必选参数
* `[]`方括号内的是可选参数
* `<>...`尖括号内后续带三个点的是可变长必选参数
* `[]...`方括号内后续带三个点的是可变长可选参数

# 权限
所有命令都必须由控制台执行

# 开源许可协议
[The Unlicense](https://unlicense.org/)