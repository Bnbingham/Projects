import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'comments-toggle',
  templateUrl: './comments-toggle.component.html',
  styleUrls: ['./comments-toggle.component.css'],
})
export class CommentsToggleComponent implements OnInit {
  @Input('post') post;
  @Output() toggled = new EventEmitter();
  isSelected;
  length;
  constructor() {}

  ngOnInit(): void {
    if (this.post.comments == undefined) {
      this.length = 0;
    } else {
      this.length = 1;
    }
  }

  onClick() {
    this.isSelected = !this.isSelected;
    this.toggled.emit();
  }
}
