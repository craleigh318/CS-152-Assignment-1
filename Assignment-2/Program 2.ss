(define alternate
  (lambda (lst)
    (cond
      ((null? lst) '())
      ((null? (cdr lst)) (cons (car lst) '()))
      (else (cons(car lst) (alternate(cddr lst)))))))