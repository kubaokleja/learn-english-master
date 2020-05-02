password = password.toUpperCase();

var passwordLength = password.length;
var mistakeCounter = 0;

var actualPassword = "";

for (i=0; i<passwordLength; i++)
{
	if (password.charAt(i)==" ") actualPassword = actualPassword + " ";
	else actualPassword = actualPassword + "-";
}

function write_password()
{
	document.getElementById("board").innerHTML = actualPassword;
}

window.onload = start;

var letters = new Array(35);
// to do with loop and ASCII 
letters[0] = "A";
letters[1] = "Ą";
letters[2] = "B";
letters[3] = "C";
letters[4] = "Ć";
letters[5] = "D";
letters[6] = "E";
letters[7] = "Ę";
letters[8] = "F";
letters[9] = "G";
letters[10] = "H";
letters[11] = "I";
letters[12] = "J";
letters[13] = "K";
letters[14] = "L";
letters[15] = "Ł";
letters[16] = "M";
letters[17] = "N";
letters[18] = "Ń";
letters[19] = "O";
letters[20] = "Ó";
letters[21] = "P";
letters[22] = "Q";
letters[23] = "R";
letters[24] = "S";
letters[25] = "Ś";
letters[26] = "T";
letters[27] = "U";
letters[28] = "V";
letters[29] = "W";
letters[30] = "X";
letters[31] = "Y";
letters[32] = "Z";
letters[33] = "Ż";
letters[34] = "Ź";



function start()
{
	
	var divContent ="";
	
	if(passwordLength != 0){
	for (i=0; i<=34; i++)
	{
		var element = "lit" + i;
		divContent = divContent + '<div class="letter" onclick="check('+i+')" id="'+element+'">'+letters[i]+'</div>';
		if ((i+1) % 7 ==0) divContent = divContent + '<div style="clear:both;"></div>';
	}
	document.getElementById("hangman").innerHTML = '<img src="/images/hangman/s0.jpg" alt="" />';
	document.getElementById("alphabet").innerHTML = divContent;
	}
	
	write_password();
}

String.prototype.setChar = function(place, charAtPlace)
{
	if (place > this.length - 1) return this.toString();
	else return this.substr(0, place) + charAtPlace + this.substr(place+1);
}


function check(nr)
{
	
	var correct = false;
	
	for(i=0; i<passwordLength; i++)
	{
		if (password.charAt(i) == letters[nr]) 
		{
			actualPassword = actualPassword.setChar(i,letters[nr]);
			correct = true;
		}
	}
	
	if(correct == true)
	{
		var element = "lit" + nr;
		document.getElementById(element).style.background = "#003300";
		document.getElementById(element).style.color = "#00C000";
		document.getElementById(element).style.border = "3px solid #00C000";
		document.getElementById(element).style.cursor = "default";
		
		write_password();
	}
	else
	{
		var element = "lit" + nr;
		document.getElementById(element).style.background = "#330000";
		document.getElementById(element).style.color = "#C00000";
		document.getElementById(element).style.border = "3px solid #C00000";
		document.getElementById(element).style.cursor = "default";	
		document.getElementById(element).setAttribute("onclick",";");		
		
		//mistake
		mistakeCounter++;
		document.getElementById("hangman").innerHTML = '<img src="/images/hangman/s'+mistakeCounter+'.jpg" alt="" />';
	}
	
	//win
	if (password == actualPassword)
	document.getElementById("alphabet").innerHTML  = "Congrats! Correct password was revealed: "+password+" ("+meaning+") "+'<br /><br /><span class="reset" onclick="location.reload()">AGAIN?</span>';
	
	//lose
	if (mistakeCounter>=9)
	document.getElementById("alphabet").innerHTML  = "You lose! Correct password: "+password+" ("+meaning+") "+'<br /><br /><span class="reset" onclick="location.reload()">AGAIN?</span>';
}
