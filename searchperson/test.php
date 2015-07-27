<?php
    $time = time();
    $secret = "042d5ca2a1d75a3d7fdee0f7c260918f";

    $content = '{"content":"xxxxx","title":"bbbb","custom_content":{"latitude":"39.980484","longitude":"116.311302"}}';
    $sign = "GETopenapi.xg.qq.com/v2/push/all_deviceaccess_id=2100087592message=".$content."message_type=2timestamp=".$time.$secret;
    echo 'http://openapi.xg.qq.com/v2/push/all_device?timestamp='.$time.'&access_id=2100087592&message_type=2&message='.$content.'&sign='.md5($sign);




//POSTopenapi.xg.qq.com/v2/push/single_deviceParam1=Value1Param2=Value2access_id=123timestamp=1386691200abcde

exit();


include "Snoopy.class.php";

$url = "http://www.163.com";
$snoopy = new Snoopy;
$snoopy->fetch($url); //获取所有内容
//echo $snoopy->results; //显示结果
$snoopy->fetchlinks($url); //获取文本内容（去掉html代码）
echo $snoopy->results;
//$snoopy->fetchlinks; //获取链接
//$snoopy->fetchform; //获取表单





exit;

    $arr = array(array("number"=>"1","name"=>"山东"),
                    array("number"=>"5","name"=>"北京"));



print_r(json_encode($arr));

exit();
$str = file_get_contents("http://121.42.44.153/area/getAll?v=1");
//echo ($str);
echo "<pre>";
var_dump(json_decode($str,true));
//count(json_decode($str,true));
exit();
class MatchEntity {
    private $matchId;
    private $matchName;
    private $fs_a;
    private $fs_b;

    function __set($property_name, $value){
        if(is_string($value)){
            if(is_null($value)){
                $this->$property_name = "";
            }else{
                $this->$property_name = $value;
            }
        }else{
                $this->$property_name = (string)$value;
        }
    }
    function toJson() {
        return json_encode($this);
    }
}

$tmp = new MatchEntity();
$tmp->matchId = 100;
$tmp->matchName = "gameInfo";
$tmp->fs_a = 1;
$tmp->fs_b = 0;
var_dump($tmp);
echo "<br>";
echo $tmp->toJson();



exit();

$obj = array(new test(),
             new test(),
             new test(),
             new test(),
             new test(),
             new test(),
             new test());

class test {
  var  $tmp1 = "1";
  var  $tmp2 = "2";
  var  $tmp3 = "3";
  var  $tmp4 = "4";

}



class json {


    public function json1($obj) {
        $list = array("list"=>$obj);
        echo json_encode($list);
    }
}

$tmp = new json();
$tmp->json1($obj);





exit();
    $arr = array("statues" => 200,
    "result" =>array("agree" => 10,
                      "review" => 10,
                      "lastLoginTime" => "2014-04-01"));

    echo "<pre>";
    echo json_encode($arr);










exit();
    $root = "http://soyue.moble.zhongsou.net/";
    $command = "user/login/";
    $params = "?url=XXXXXXXXXXXXXXXX";
    $params = array("url=XXXXXXXXXXXXXXXXXX",
                                "url=XXXXXXXXXXXXXXXXXXXX",
                                "url=XXXXXXXXXXXXXXXXXXX");

for($j = 0;$j <length($params);$j++){
    $url = $root.$command.$params[j];
    $startTime = time();
    for($i = 0;$i <100;$i++){
        file_get_contents($url);
    }
    $endTime = time();
    echo ($endTime - $startTime)/100;
}






exit();
//    apd_set_pprof_trace();
    function tmp() {
        echo "this is source tmp";
    }







tmp();











?>