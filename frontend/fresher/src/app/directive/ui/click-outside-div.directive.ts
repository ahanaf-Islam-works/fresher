import {
  Directive,
  ElementRef,
  EventEmitter,
  HostListener,
  Output,
} from '@angular/core';

@Directive({
  selector: '[appClickOutsideDiv]',
  standalone: true,
})
export class ClickOutsideDivDirective {
  constructor(private element: ElementRef) {}
  @Output() clickOutsideDiv = new EventEmitter();

  @HostListener('document:click', ['$event.target'])
  public onClick(targetElement: any): void {
    const clickedInside = this.element.nativeElement.contains(targetElement);
    if (!clickedInside) {
      this.clickOutsideDiv.emit(targetElement);
    }
  }
}
