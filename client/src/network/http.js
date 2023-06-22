export default async function httpFetch(url, options) {
  const data = await fetch(`${url}`, {
    ...options,
    header: {
      "Content-Type": "application/json",
      ...options.headers,
    },
  });
  if (data.status < 200 || data.status > 299) {
    return alert("오류가 발생했습니다.");
  }
  const response = await data.json();
  return response.data;
}
