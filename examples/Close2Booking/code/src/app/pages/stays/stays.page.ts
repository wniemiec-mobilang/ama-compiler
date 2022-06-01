import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'stays-page',
  templateUrl: 'stays.page.html',
  styleUrls: ['stays.page.scss'],
  encapsulation: ViewEncapsulation.None
})
export class StaysPage implements OnInit {

  constructor(private routeParams: ActivatedRoute) {
  }

  ngOnInit(): void {
document.getElementById("_98c904aabf7bf585ce06888aad648c28").onclick = () => handleBack()
function loadData() {
const id=this.routeParams.snapshot.params.q.split('site__eq__')[1].split('&')[0]
const body=document.querySelector(".body")
const header=document.querySelector(".header-text")
fetch("http://192.168.0.62:8080/site/"+id).then((res) => {
if (res.status!=200) {
return
}
res.json().then((data) => {
header.innerHTML=data.name+" . Jun 4 - Jun 5"
for (let stay of data.stays) {
let rating=0
for (let review of stay.reviews) {
rating+=review.rating
}
rating/=stay.reviews.length
body.innerHTML+=`<div class="item"><div class="left"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/close2booking/assets/images/${stay.image}" /><p>Breakfast included</p></div><div class="right"><div class="row1"><h1>${stay.name}</h1><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/favorite-black.png" height="15" /></div></div><div class="row2"><div class="rating"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/genius-star.png" height="15" /></div></div><div class="row3"><p class="rate">${rating}</p><small><span class="highlight">${rating>9 ? "Very good" : rating>8 ? "good" : "ok"}</span> . ${stay.reviews.length} reviews</small></div><div class="row4"><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/place-black.png" height="15" /></div><p>${stay.location}</p></div><div class="row5" ${stay.eco ? "" : "style='display:none;'"}><div class="icon"><img src="https://www.inf.ufrgs.br/~wniemiec/shared/img/leaf-black.png" height="15" /></div><p>Travel Sustainable</p></div><p class="highlight-accent" ${stay.mobile ? "" : "style='display:none;'"}>Mobile-only price</p><div class="row6"><p>${stay.roomType}</p><p>Hotel room: ${stay.totalBeds>1 ? stay.totalBeds+" beds" : stay.totalBeds+" bed"}</p></div><div class="row7"><p>${stay.priceDescription}</p></div><div class="row8"><span class="before">R$ ${stay.oldPrice}</span><span class="current">R$ ${stay.newPrice}</span></div><div class="row9"><p>${stay.fee>0 ? "+ R$ 3 taxes and charges" : "Includes taxes and fees"}</p></div></div></div>`
}
})
}).catch((e) => {
})
}
loadData()
function handleBack() {
window.location.href="quick-trips"
}

  }
}
