//    Get ID from "Add Direction" button clicked (ID on button will have to increment each time.)
//    Add one to that button and insert on ID for next direction input.
//    Hide original button, and add a new add and a remove button to newly inserted input.

function addAnotherDirection() {
let directionFieldString = document.getElementById("directionFieldString");
let li = document.createElement("li");
let input = document.createElement("input");
    input.setAttribute("id","directionsString");
    input.setAttribute("name","directionsString");
li.appendChild(input);
directionFieldString.appendChild(li);
}

//$(function(){
//    $('#directionsSection').find('li').each(function(){
//        let i = $(this).attr("id").slice(-1);
//        let id ='directionsList'+ i;
//        $(this).attr('id',id);
//        ++i;
//        console.log($(this).attr('id'));
//    });
// });
//    document.getElementById("addDirection").style.backgroundColor ="blue";

//window.addEventListener("load", function()) {
//
//}

//    JQUERY
//$(document).ready(function() {
//
//    function reloadDirections (html) {
//        $('#directionsSection').replaceWith($(html));
//    }
//
//    $('#addDirection').click(function (event) {
//        event.preventDefault();
//        let data = $('form').serialize();
//        data += 'addDirection';
//        $.post('/add', data, reloadDirections);
//    });
//
//});

//        let lineVar = 1;
//        $("#addDirection").click(function () {
//            $("#directionLine").clone().appendTo("ol");
//        });
//    });

    //let form = document.querySelector;

//    let addIngredient = null;
//
//    function addAnotherIngredient() {
//    addIngredient = document.geElementById("ingredientBlock");
//
//    $('#ingredientBlock').clone().appendTo('#ingredientSection');
//
//    };