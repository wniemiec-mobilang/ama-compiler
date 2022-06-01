import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'home-page',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomePage implements OnInit {
  input_rooms = "";
  input_date = "";
  input_location = "";

  constructor(private routeParams: ActivatedRoute) {
  }

  ngOnInit(): void {
document.getElementById("_b41467c24424dbfa041c01f3c47e637e").onclick = () => window.location.href='mobilang::screen::quick-trips'
function loadUser() {
const body=document.querySelector(".carrosel")
const defaultMessage="Enjoy discounts at participating properties worldwide"
const lockedMessage="Complete 5 stays to unlock Genius Level 2"
fetch("http://192.168.0.62:8080/user/").then((res) => {
if (res.status!=200) {
return
}
res.json().then((data) => {
body.innerHTML=`<div class="box-special-small"><p>${data.name}, you're at <span class="highlight">Genius Nivel ${data.level}</span> in our loyalty program</p><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/genius-globe.png" height="15" /></div></div><div class="box-small ${data.level>=1 ? "enabled" : ""}"><div class="header"><h2>10% discounts</h2><div class="icon highlight"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/percent-blue.png" height="15" /></div></div><p>${data.level>=1 ? defaultMessage : lockedMessage}</p></div><div class="box-small ${data.level>=2 ? "enabled" : ""}"><div class="header"><h2>15% discounts</h2><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/lock-gray.png" height="15" /></div></div><p>${data.level>=2 ? defaultMessage : lockedMessage}</p></div><div class="box-small ${data.level>=2 ? "enabled" : ""}"><div class="header"><h2>Free breakfasts</h2><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/lock-gray.png" height="15" /></div></div><p>${data.level>=2 ? defaultMessage : lockedMessage}</p></div><div class="box-small ${data.level>=2 ? "enabled" : ""}"><div class="header"><h2>Free room upgrades</h2><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/lock-gray.png" height="15" /></div></div><p>${data.level>=2 ? defaultMessage : lockedMessage}</p></div>`
})
}).catch((e) => {
})
}
loadUser()

  }
}
