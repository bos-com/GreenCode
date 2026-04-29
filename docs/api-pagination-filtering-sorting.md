# API Pagination, Filtering, and Sorting

This document defines how GreenCode backend APIs should implement pagination, filtering, and sorting across all list endpoints. Frontend developers and contributors should follow these conventions when building or consuming list-based APIs.

---

##  Pagination

All list endpoints must support cursor- or offset-based pagination using the following query parameters:

| Parameter | Type    | Default | Description                        |
|-----------|---------|---------|------------------------------------|
| `page`    | integer | `0`     | Zero-indexed page number           |
| `size`    | integer | `20`    | Number of results per page (max 100) |

### Example Request
### Example Response

```json
{
  "content": [...],
  "page": 0,
  "size": 10,
  "totalElements": 87,
  "totalPages": 9,
  "last": false
}
```

---

##  Filtering

Filters are passed as query parameters. Multiple filters are combined with AND logic.

| Parameter     | Example Value     | Description                        |
|---------------|-------------------|------------------------------------|
| `status`      | `active`          | Filter by project or resource status |
| `category`    | `sustainability`  | Filter by category tag             |
| `createdAfter`| `2024-01-01`      | Filter records created after date  |
| `createdBefore`| `2024-12-31`     | Filter records created before date |
| `search`      | `green farm`      | Full-text search on name/description |

### Example Request
---

##  Sorting

Sorting is controlled by `sort` and `direction` query parameters.

| Parameter   | Values                        | Default      |
|-------------|-------------------------------|--------------|
| `sort`      | `createdAt`, `name`, `status` | `createdAt`  |
| `direction` | `asc`, `desc`                 | `desc`       |

### Example Request
---

##  Combined Example

Retrieve the second page of active sustainability projects, sorted by name ascending:
---

##  Backend Implementation Notes (Spring Boot)

Use Spring Data's `Pageable` interface for pagination and sorting:

```java
@GetMapping("/projects")
public Page<ProjectDTO> getProjects(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size,
    @RequestParam(defaultValue = "createdAt") String sort,
    @RequestParam(defaultValue = "desc") String direction,
    @RequestParam(required = false) String status,
    @RequestParam(required = false) String search
) {
    Pageable pageable = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), sort));
    return projectService.findAll(status, search, pageable);
}
```

---

##  Frontend Implementation Notes (React)

Use Axios with query parameters:

```javascript
const fetchProjects = async ({ page = 0, size = 10, sort = 'createdAt', direction = 'desc', status, search }) => {
  const params = { page, size, sort, direction };
  if (status) params.status = status;
  if (search) params.search = search;

  const response = await axios.get('/api/projects', { params });
  return response.data;
};
```

---

##  Checklist for Contributors

When adding a new list endpoint:
- [ ] Support `page` and `size` query params
- [ ] Return pagination metadata in response body
- [ ] Support at least `search` filter
- [ ] Support `sort` and `direction` params
- [ ] Document the endpoint in Swagger/OpenAPI annotations
