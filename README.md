# EPAM Java project
![alt text](http://i.piccy.info/i9/de4f66c0857d8dfa6f3d6818db8a6f7b/1489178107/74349/1127159/Screenshot_20170310_233435.png)

Start REST:
 cd rest
 mvn jetty:run

Start WEB app:
 cd js-client
 opera index.html
 
 Try CURL requests like:
  curl -v "localhost:8088/students/"
  curl -v "localhost:8088/students?minGpa=5&maxGpa=9&groupId=1"
  curl -v "localhost:8088/students/id1"
  curl -H "Content-Type: application/json" -X POST -d '{"name":"NewStudent","gpa":4.2,"groupId":1}' -v "localhost:8088/students"
  curl -v "localhost:8088/students/count"
  curl -v "localhost:8088/students/gpa"
  curl -H "Content-Type: application/json" -X PUT -d '{"studentId":1,"name":"NewStudent","gpa":4.2,"groupId":1}' -v "localhost:8088/students"
  curl -X DELETE localhost:8088/students/id{id}
  curl -v "localhost:8088/groups?minGradDate=2016-01-01&maxGradDate=2017-12-31"
  curl -H "Content-Type: application/json" -X POST -d '{"name":"II-13","graduationDate":"2019-05-31"}' -v "localhost:8088/groups"
  curl -H "Content-Type: application/json" -X PUT -d '{"groupId":1,"name":"II-13-UPT","graduationDate":"2222-05-31"}' -v "localhost:8088/groups"
  curl -v "localhost:8088/groups/id1"
  curl -v "localhost:8088/groups/II-12"
  curl -X DELETE localhost:8088/group/id{id}
  curl -v "localhost:8088/groups/count"
