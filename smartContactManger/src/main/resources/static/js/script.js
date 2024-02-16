const toggleSidebar = () => {
    const sidebar = $(".sidebar");
    const content = $(".content");

    if (sidebar.is(":visible")) {
        // Sidebar is visible, hide it
        sidebar.hide();
        content.css("margin-left", "0%");
    } else {
        // Sidebar is not visible, show it
        sidebar.show();
        content.css("margin-left", "20%");
    }
};

const search=()=>{
let query=$("#search-input").val();

if(query==""){
$(".search-result").hide();

}
else{
    console.log(query);
    // sedning resquest to the server
     let url=` http://localhost:8080/search/${query}`;
     fetch(url)
     .then((response)=>
     {
		 return response.json();
	 })
	 .then((data)=>
     {
	console.log(data);
	
	let text=` <div class='list-group'>` ;
	data.forEach((contact)=> {
	text += ` <a  href='/user/${contact.cid}/contact' class='list-group-item list-group-action'> ${contact.name} </a> `	
	});
	text += `</div>`;
	$(".search-result").html(text);
	$(".search-result").show();
	
	 });
    
    $(".search-result").show();
}
};


