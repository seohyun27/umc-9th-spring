## 특정 지역에 가게 추가하기

### 1. 가게 엔티티(req)

```json
{
  "address": 상세주소,
  "name": 가게 이름,
  "open_time": 오픈 시간,
  "close_time": 문 닫는 시간,
  "category": 가게 카테고리,
  "manager": 매니저 아이디,
  "region" : 지역
}
```
+ DB 설계할 땐 안했지만 추가로 manager도 필요하겠다는 생각을 하였다.
+ member에는 타입으로 넣어놓고 store에는 안 넣었음....
### 2. res
```json
{
  "id": 가게 아이디
}
```
### 3. valid
```json
{
  "address": @NotNull,@Max,
  "name": @NotBlank,@Max,
  "category": @ExistCategory,
  "manager": @ManagerPermission,
  "region" : @ExistRegion,
}
```
+ manager는 jwt를 쓴다면 자동으로 들어갈 거지만
+ 아직은 구현을 안 했으므로 body에 valid 체크

### 4. 궁금한 점
```java
public class StoreConverter {

    //Entity->DTO
    public static StoreResDTO.registerDTO toRegisterDTO(Store store)
    {
        return StoreResDTO.registerDTO.builder()
                .storeId(store.getId())
                .build();
    }

    //DTO->Entity
    public static Store toStore(StoreReqDTO.registerDTO dto, Member member, Category category, Region region)
    {
        return Store.builder()
                .name(dto.name())
                .address(dto.address())
                .open_time(dto.openTime())
                .close_time(dto.closeTime())
                .member(member)
                .region(region)
                .category(category)
                .build();
    }
}
```
store 객체엔 member 가 들어가야 하고 dto엔 long 타입으로 받는데
어떻게 전환함???
일단 service에서 변환 후 컨버터에 넣는 걸로 함
### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 일반 valid check : 모두 null이거나 blank
###### -> 기대: address,name,category,member,region에서 모두 에러 표기해야 함
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "address": "공백일 수 없습니다",
    "member": "널이어서는 안됩니다",
    "name": "공백일 수 없습니다",
    "category": "널이어서는 안됩니다",
    "region": "널이어서는 안됩니다"
  }
}
```
##### 2) 문자열 길이 valid check
###### -> 기대 : address,name에서 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "address": "크기가 0에서 50 사이여야 합니다",
    "name": "크기가 0에서 10 사이여야 합니다"
  }
}
```
##### 3) 존재하지 않을 때 valid check : @exist
###### -> 기대 : category,member,region에서 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "member": "해당 사용자를 찾지 못했습니다.",
    "region": "해당 지역이 존재하지 않습니다.",
    "category": "카테고리가 존재하지 않습니다."
  }
}
```
##### 4) 권한 valid check : @permission
###### -> 기대 : member에서 권한 오류 (매니저 권한이 아닌 일반 사용자로 넣었을 때)
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "member": "권한이 없습니다."
  }
}
```
##### 5) 가게 등록 : 모두 정상 입력값을 넣어줬을 때
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
```json
{
  "isSuccess": true,
  "code": "STORE201_1",
  "message": "가게 등록이 완료되었습니다.",
  "result": {
    "storeId": 1
  }
}
```
###### -> DB
```json
[
    {
        "store_id": 1,
        "store_address": "대구",
        "close_time": "12:00:00",
        "store_name": "김밥천국",
        "open_time": "10:00:00",
        "category_id": 1,
        "manager_id": 1,
        "region_id": 1
    }
]
```


