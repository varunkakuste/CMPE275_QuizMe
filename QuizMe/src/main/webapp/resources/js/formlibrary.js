function saveToLocalStorage()
{ 
    localStorage.setItem("name", document.form1.name.value); 
    localStorage.setItem("email", document.form1.email.value); 
    localStorage.setItem("subject", document.form1.subject.value);
    localStorage.setItem("message", document.form1.msg.value);
}



    function isValidEmail(obj)
{
    
    var uemail = obj.value;
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
        if(uemail.value.match(mailformat))  
        {  
            return true;  
        }  
        else  
        {  
            alert("Enter valid email address!");  
            uemail.focus();  
            return false;  
        }  
}