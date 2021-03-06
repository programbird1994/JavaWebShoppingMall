﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>uploadify 多文件上传例子</title>
<script src="scripts/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="scripts/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="scripts/uploadify/uploadify.css">

<script type="text/javascript">  
        $(function() {  
            $('#projectfile').uploadify({  
                'swf'      : 'scripts/uploadify/uploadify.swf',  
                'uploader' : 'uploadfile',  
                'formData': {'projectid': '${project.id}'},  
                'fileObjName' : 'file',  
                'fileSizeLimit' : '0',  
                'successTimeout' :10,
                'auto':false,
                'onQueueComplete' : function(queueData) {  
                    alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');  
                    }  
                  
            });  
        });  
</script>  
<style type="text/css">
body {
    font: 13px Arial, Helvetica, Sans-serif;
}
 
.haha {
    color: #FFFFFF;
}
 
#queue {
    background-color: #FFF;
    border-radius: 3px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
    height: 103px;
    margin-bottom: 10px;
    overflow: auto;
    padding: 5px 10px;
    width: 300px;
}
</style>
</head>
 
<form>  
    <input id="projectfile" name="file" type="file" >提交</input>  
</form>  
</html>