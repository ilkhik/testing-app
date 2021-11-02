const ANSWER_HTML = '<li class="answer"><input placeholder="Введите вариант ответа" class="answerText" required><input type="checkbox" class="answerIsCorrect"><button class="removeAnswerButton">-</button></li>';
const QUESTION_HTML = '<li class="question">\
                <input placeholder="Введите вопрос" class="questionText" required>\
                <select class="questionKind">\
                    <option value="SINGLE_CHOICE">Один вариант ответа</option>\
                    <option value="MULTIPLY_CHOICE">Несколько вариантов ответа</option>\
                </select><ol>' + ANSWER_HTML + '<button class="addAnswerButton">+</button></ol>\
                <button class="removeQuestionButton">Удалить вопрос</button>\
            </li>';

function serializeQuestion(q){
    let data = {};
    data["text"] = $(q).find(".questionText").val();
    data["questionKind"] = $(q).find(".questionKind").val();
    let answers = [];
    $(q).find(".answer").each(function(){
        let answer = {};
        answer["text"] = $(this).find(".answerText").val();
        answer["isCorrect"] = $(this).find(".answerIsCorrect").is(":checked");
        answers.push(answer);
    });
    data["answers"] = answers;
    return data;
}


function serializeTest(){
    let data = {};
    data["title"] = $("#testTitle").val();
    
    let questions = [];
    $(".question").each(function(){
        questions.push(serializeQuestion(this));
    });
    
    data["questions"] = questions;
    return data;
}

$(document).ready(function(){
    $(document).on("click", ".addAnswerButton", function(){
        // $(this).before(ANSWER_HTML);
        $(this).parents(".question").find(".answer").last().after(ANSWER_HTML);
    });
    
    $(document).on("click", ".removeAnswerButton", function(){
        $(this).parents(".answer").remove();
    });
    
    $(document).on("click", ".removeQuestionButton", function(){
        $(this).parents(".question").remove();
    });
    
    $(document).on("click", ".addQuestionButton", function(){
        $(this).parents("#test").find(".question").last().after(QUESTION_HTML);
    });
    
    $(document).on("click", ".answerIsCorrect", function(){
        let questionKind = $(this).parents(".question").find(".questionKind").val();
        if (questionKind === "SINGLE_CHOICE"){
            $(this).parents(".question").find(".answerIsCorrect").each(function(){
                $(this).prop("checked", false);
            });
            $(this).prop("checked", true);
        }
    });
    
    $(document).on("submit", "#test", function(e){
        e.preventDefault();
        $.ajax({
           type: "POST",
           url: $(this).attr("action"),
           contentType: "application/json; charset=utf-8",
           data: JSON.stringify(serializeTest()),
           success: function(data){
               window.location.replace("/tests");
           },
           error: function(){
               alert("Error!");
               window.location.replace("/tests");
           }
         });
    });
});
