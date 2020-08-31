import { User } from './User';
import { Post } from './Post';
export interface UserData {
  user: User;
  posts: Post[];
  comments: Comment[];
}
