window.addEventListener("load", function() {

    function addAnotherIngredient() {
        let ingredientList = document.querySelectorAll("li.ingredientLine");
        let ingredientId;

        for (let item of ingredientList) {
            ingredientId = item.id;
            if(getComputedStyle(item, null).display === "none") {
                document.getElementById(ingredientId).style.display = "block";
                break;
            }
        }
    }

    function addAnotherDirection() {
        let directionFieldString = document.getElementById("directionFieldString");
        let li = document.createElement("li");
        let input = document.createElement("input");

        input.setAttribute("id","directionsString");
        input.setAttribute("name","directionsString");

        li.appendChild(input);
        directionFieldString.appendChild(li);
    }

    let ingredient = document.getElementById("addIngredient");
    ingredient.addEventListener("click", function() {
        addAnotherIngredient();
    });

    let direction = document.getElementById("addDirection");
    direction.addEventListener("click", function() {
        addAnotherDirection();
    });

    let edit = document.getElementById("ingredientLineEdit");
    if (edit != null) {
        addAnotherIngredient();
    }
});


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
//}):