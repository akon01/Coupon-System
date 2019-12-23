import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-footer",
  templateUrl: "./footer.component.html",
  styleUrls: ["./footer.component.css"]
})
export class FooterComponent implements OnInit {
  public date = null;

  constructor() {}
  /**
   * Show current date.
   */
  ngOnInit() {
    this.date = new Date();
  }
}
