# android-shopping-order

## 1. ProductListPresenter

- [x] 상품 목록 불러오기

```Gherkin
GIVEN 상품을 불러올 수 있는 상태다. 
WHEN 상품을 목록 요청을 보낸다.
THEN 상품을 노출시킨다.
```

- [x] 최근 본 상품 목록 불러오기

```Gherkin
GIVEN 최근 본 상품을 불러올 수 있는 상태다.
WHEN 최근 본 상품 요청을 보낸다.
THEN 최근 본 상품을 노출시킨다.
```

- [x] 상품 상세 보여주기

```Gherkin
GIVEN 상품 상세를 보여줄 수 있는 상태다.
WHEN 상품 상세를 보여달라는 요청을 보낸다.
THEN 상품 상세 화면이 노출된다.
AND 최근 본 상품에 저장된다.
```

- [x] 장바구니 상품 개수 증가

```Gherkin
GIVEN 장바구니의 상품 개수를 증가시킬 수 있는 상태다. 
WHEN 장바구니 상품 개수 증가 요청을 보낸다.
THEN 증가된 상품 개수가 노출된다.
```

- [x] 장바구니의 상품 개수 감소

```Gherkin
GIVEN 장바구니의 상품 개수를 감소시킬 수 있는 상태다. 
WHEN 장바구니 상품 개수 감소 요청을 보낸다.
THEN 감소된 상품 개수가 노출된다.
```

## 2. CartPresenter

- [x] 장바구니 목록 불러오기

```Gherkin
GIVEN 장바구니 목록을 불러올 수 있는 상태다. 
WHEN 장바구니 목록 불러오기 요청을 보낸다.
THEN 장바구니 목록이 노출된다.
```

- [x] 장바구니 아이템을 삭제한다.

```Gherkin
GIVEN 장바구니 아이템을 삭제할 수 있는 상태다.
WHEN 장바구니 아이템 삭제 요청을 보낸다.
THEN 삭제되고 남은 아이템이 노출된다.
```

- [x] 장바구니 아이템의 상품 개수를 증가시킨다.

```Gherkin
GIVEN 장바구니 아이템의 상품 개수를 증가시킬 수 있는 상태다.
WHEN 장바구니 아이템 상품 개수 증가 요청을 보낸다.
THEN 장바구니 아이템 상품 개수가 증가되어 화면에 노출된다.
```

- [x] 장바구니 목록 하나의 상품 개수를 감소시킨다.

```Gherkin
GIVEN 장바구니 아이템의 상품 개수를 감소시킬 수 있는 상태다.
WHEN 장바구니 아이템 상품 개수 감소 요청을 보낸다.
THEN 장바구니 아이템 상품 개수가 감소되어 화면에 노출된다.
```

- [x] 장바구니 목록 하나의 선택 상태를 변경한다.

```Gherkin
GIVEN 장바구니 아이템의 선택 상태를 변경할 수 있는 상태이다.
WHEN 장바구니 아이템 선택 상태 변경 요청을 보낸다.
THEN 전체 선택 여부를 확인한다.
AND 선택된 상품 전체 가격을 업데이트 한다.
AND 선택된 상품 전체 개수를 업데이트 한다.
```

- [x] 장바구니 목록 전체를 선택한다.

```Gherkin
GIVEN 장바구니 목록 전체를 선택할 수 있는 상태이다.
WHEN 장바구니 목록 전체 선택 요청을 보낸다.
THEN 전체 선택 여부를 확인한다.
AND 선택된 상품 전체 가격을 업데이트 한다.
AND 선택된 상품 전체 개수를 업데이트 한다.
```

- [x] 다음 페이지로 넘긴다

```Gherkin
GIVEN 다음 페이지로 넘길 수 있는 상태다.
WHEN 다음 페이지로 넘기기 요청을 보낸다.
THEN 다음 페이지 장바구니 상품들이 노출된다.
```

- [x] 이전 페이지로 돌아간다.

```Gherkin
GIVEN 이전 페이지로 돌아갈 수 있는 상태다.
WHEN 이전 페이지로 돌아가기 요청을 보낸다.
THEN 이전 페이지 장바구니 상품들이 노출된다.
```

- [ ] 장바구니 상품을 주문하기 위해 주문 화면으로 이동한다.

```Gherkin
GIVEN 주문할 수 있는 상태이다.
WHEN 주문하기 이동 요청을 보낸다.
THEN 이전 페이지 장바구니 상품들이 노출된다.
```
