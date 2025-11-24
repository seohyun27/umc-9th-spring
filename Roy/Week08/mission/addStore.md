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
2. res
```json
{
  "id": 가게 아이디
}
```
3. valid
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

4. 궁금한 점
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