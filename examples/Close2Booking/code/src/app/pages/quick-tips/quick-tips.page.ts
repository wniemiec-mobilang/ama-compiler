import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'quick-tips-page',
  templateUrl: 'quick-tips.page.html',
  styleUrls: ['quick-tips.page.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuickTipsPage implements OnInit {

  constructor(private routeParams: ActivatedRoute) {
  }

  ngOnInit(): void {
document.getElementById("_fe0314d6059b6be64fc9bfa29a824df5").onclick = () => handleBack()
function loadQuick() {
loadData("quick")
}
function loadData(title) {
const body=document.querySelector(".carrosel."+title)
fetch("http://192.168.0.62:8080/sites/"+title).then((res) => {
if (res.status!=200) {
return
}
res.json().then((data) => {
for (let site of data) {
let avgPrice=0
for (let stay of site.stays) {
avgPrice+=stay.newPrice
}
avgPrice/site.stays.length
body.innerHTML+=`<button class="box"  id="_52619b4dcfdf72e28bafbb3fb6d2aedb"><div class="image"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/close2booking/assets/images/${site.image}" /></div><div class="header"><h1>${site.name}</h1><div class="info"><div class="item-row"><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/place-black.png" /></div><p>${site.stays[0].location.split(" ")[0]} away</p></div><div class="item-row"><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/bed-black.png" /></div><p>Avg. price/night: R$ ${avgPrice}</p></div></div></div></button>`;document.getElementById("_52619b4dcfdf72e28bafbb3fb6d2aedb").onclick = () => window.location.href
}
})
}).catch((e) => {
})
}
function loadNature() {
loadData("nature")
}
loadQuick()
loadNature()
function handleBack() {
window.location.href="home.html"
}

  }
}
