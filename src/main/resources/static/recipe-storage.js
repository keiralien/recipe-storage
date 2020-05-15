window.addEventListener("load", function()) {
    //let form = document.querySelector;

    let addIngredient = null;

    function addAnotherIngredient() {
//    addIngredient = document.geElementById("ingredientBlock");

    $('#ingredientBlock').clone().appendTo('#ingredientSection');

    };

//    Get ID from "Add Direction" button clicked (ID on button will have to increment each time.)
//    Add one to that button and insert on ID for next direction input.
//    Hide original button, and add a new add and a remove button to newly inserted input.
    function addAnotherDirection() {

    $('#directionLine').clone().appendTo('#ol');

    };

}

//$(document).ready(function() {
//    $("#addIngredient").click(function() {
//        $("#ingredientBlock").clone();
//    });
//    $("#addDirection").click(function() {
//        $("#directionLine").clone().appendTo("ol");
//    });
//});