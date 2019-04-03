import { post, get } from '@/utils/request'

export function login(username, password) {
  return post('/auth/login', {
    'username': username,
    'password': password
  }
  )
}

export function getInfo(token) {
  return get('/user/info', { 'x-token': token })
}

export function logout() {
  return get('/user/logout')
}
