Sample project of Spring boot and sample API

Những note chính:
 + Controller: Chỗ để tạo API URL, RestController là return JSON only. Còn nếu để @Controller nó sẽ return nhiều loại.
 + Tạo thêm layer services rồi code theo mô hình MVC bình thường.
 + Run FAMainApplication để chạy project, debug / run normally tùy.
   - Tuyệt đối không sửa tên file Main.
 + Lỗi có comment trong APIController rồi.
 + Giữ nguyên version Spring trong pom file.
 + Có thể add them libs nhưng không remove bất kì libs nào hiện tại
 + Viết test trong folder src/test/java. Tạo package y change như src/main/java
 + Stored file trong folder src/main/resources.
 + Request body là bắt buộc phải có body, ít nhất phải map được 1 field với Entity mới thỏa điều kiện nếu không sẽ ăn 400.
 
 Về project:
 - Project sử dụng thư viện Spring boot (Spring framework).
 - Nó có cơ chế handle giúp mọi người tất cả mọi thứ nếu như define đúng ( giống như việc dùng @GetMapping, nếu chỉ dùng Servlet Java thuần thì việc define 1 url như vậy tốn khoảng 3 files XML. Còn đối với Spring boot thì chỉ cần dùng @Controller / @RestController sau đó define những API với GET/POST/PUT/DELETE Mapping là nó đã hoàn thành 1 lifecyles cần thiết.
 - Về cơ chế thì đây là 1 webserver ( có host/port) chứ không phải 1 project Java thông thường mà các bạn hay code bằng console. 
 - Để chạy đươc nó thì cần phải có Tomcat Server (host web trên local) nhưng mà do các bạn chưa học tới nên ở đây Spring boot có 1 Tomcat server embedeed bên trong libs của nó luôn nên khi start Main file là nó bật server rồi.
 - Web application nên các bạn chạy lỗi nó cũng không dừng chương trinh, chương trình chỉ dừng khi các bạn bấm nút màu đỏ ( Stop server).
 - Spring boot quản lý những Object theo cơ chế Singleton pattern, mỗi một object mang dấu @ bên trên Spring boot sẽ hiểu đây là 1 Bean (thuật ngữ object trong Spring). Mỗi một Bean sẽ được đưa vào bộ máy Spring Factory để Spring quản lý, đó là lý do mà khi các bạn đặt @RestController Spring nó biết đây là 1 Bean nên nó sẽ quản lý theo cách của nó => các bạn quăng exception cỡ nào đi nữa thì Spring cũng sẽ vứt exception về cho người dùng chứ không dừng chương trình. 
 - Còn các @ như Service và Repository đại diện cho Service và DAO nhưng ở scope hiện tại các bạn chưa hiểu để sử dụng nên khuyến khích là KHÔNG sử dụng tới nó, cứ code như bình thường. Controller call Service (new Service rồi call).
 
Về log:
- Trong folder src/main/resource có file logback-spring.xml. File này sẽ configure log ra để tiện cho người khác / bạn trace lại log, log sẽ được ghi vào folder /log/fa.log
- Đọc thêm ở đây https://lankydan.dev/2019/01/09/configuring-logback-with-spring-boot

Về config:
- File application-default.properties là file config những properties của Spring. Tuyệt đối KHÔNG sửa nếu không hiểu nó là gì. Chỉ được sửa server.port=8080 thành port mình thích