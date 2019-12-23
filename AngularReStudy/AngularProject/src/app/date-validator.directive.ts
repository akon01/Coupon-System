import { Directive } from "@angular/core";
import {
  NG_VALIDATORS,
  Validator,
  AbstractControl,
  ValidationErrors
} from "@angular/forms";

@Directive({
  selector: "[appDateValidator][ngModel]",
  providers: [
    { provide: NG_VALIDATORS, useExisting: DateValidatorDirective, multi: true }
  ]
})
/**
 * validates that the date is after today
 */
export class DateValidatorDirective implements Validator {
  validate(control: AbstractControl): ValidationErrors {
    const today = new Date().valueOf();
    let date: Date = new Date(control.value);
    if (today > date.valueOf()) {
      return {
        dateValidator: {
          valid: false
        }
      };
    } else {
      return null;
    }
  }

  registerOnValidatorChange?(fn: () => void): void {}

  constructor() {}
}
