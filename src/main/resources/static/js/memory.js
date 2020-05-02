
var c0 = document.getElementById('c0');
var c1 = document.getElementById('c1');
var c2 = document.getElementById('c2');
var c3 = document.getElementById('c3');

var c4 = document.getElementById('c4');
var c5 = document.getElementById('c5');
var c6 = document.getElementById('c6');
var c7 = document.getElementById('c7');

var c8 = document.getElementById('c8');
var c9 = document.getElementById('c9');
var c10 = document.getElementById('c10');
var c11 = document.getElementById('c11');

c0.addEventListener("click", function(){revealCard(0);});
c1.addEventListener("click", function(){revealCard(1);});
c2.addEventListener("click", function(){revealCard(2);});
c3.addEventListener("click", function(){revealCard(3);});

c4.addEventListener("click", function(){revealCard(4);});
c5.addEventListener("click", function(){revealCard(5);});
c6.addEventListener("click", function(){revealCard(6);});
c7.addEventListener("click", function(){revealCard(7);});

c8.addEventListener("click", function(){revealCard(8);});
c9.addEventListener("click", function(){revealCard(9);});
c10.addEventListener("click", function(){revealCard(10);});
c11.addEventListener("click", function(){revealCard(11);});

var oneVisible=false;
var turnCounter=0;
var visible_nr;
var firstNumber;
var secondNumber;
var lock=false;
var pairsLeft = 6;
var copyCardsArray = [...cards];
var randomTable = shuffle(copyCardsArray);
console.log(cards);
function revealCard(nr)
{
	var opacityValue = $('#c'+nr).css('opacity');
	//alert(nr);
	if(opacityValue !=0 && lock == false){
		lock = true;
		$('#c'+nr).html('<p>'+randomTable[nr]+'</p>');
		$('#c'+nr).css('background-image', "none");
		$('#c'+nr).addClass('cardA');
		$('#c'+nr).removeClass('card');
		
		if(oneVisible == false)
		{
			for (index = 0; index < cards.length; index++) { 
			    if(cards[index]==randomTable[nr])
			    	{
			    		firstNumber=index;
			    	}
			} 
			oneVisible = true;
			visible_nr = nr;
			lock = false;
		}
		else
		{
			for (index = 0; index < cards.length; index++) { 
			    if(cards[index]==randomTable[nr])
			    	{
			    		secondNumber=index;
			    	}
			} 
			if(firstNumber + secondNumber == 11)
			{
				setTimeout(function() {hideTwoCards(nr, visible_nr)}, 750);
				
			}
			else
			{
				setTimeout(function() {restoreTwoCards(nr, visible_nr)}, 1000);
			}
			turnCounter++;
			$('.score').html('Turn counter: '+turnCounter);
			oneVisible = false;
		}
	}
}

function hideTwoCards(numberOne, numberTwo)
{
	$('#c'+numberOne).css('opacity','0');
	$('#c'+numberTwo).css('opacity','0');
	$('#c'+numberOne).html("");
	$('#c'+numberTwo).html("");
	
	pairsLeft--;
	if(pairsLeft == 0)
	{
		$('.board').html('<h1> You win! <br>Done in '+turnCounter+' turns :)</h1>');
	}
	firstNumber=0;
	secondNumber=0;
	lock = false;
}


function restoreTwoCards(numberOne, numberTwo)
{
	$('#c'+numberOne).css('background-image', 'url("/images/messi.png")');
	$('#c'+numberOne).addClass('card');
	$('#c'+numberOne).removeClass('cardA');
	$('#c'+numberOne).html("");
	
	$('#c'+numberTwo).css('background-image', 'url("/images/messi.png")');
	$('#c'+numberTwo).addClass('card');
	$('#c'+numberTwo).removeClass('cardA');
	$('#c'+numberTwo).html("");
	firstNumber=0;
	secondNumber=0;
	lock = false;
}

function shuffle(a) {
    var j, x, i;
    for (i = a.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
    return a;
}