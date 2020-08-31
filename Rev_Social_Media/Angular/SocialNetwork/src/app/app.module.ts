import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostComponent } from './post/post.component';
import { CommentContainerComponent } from './comment-container/comment-container.component';
import { LinkContainerComponent } from './link-container/link-container.component';
import { PostContainerComponent } from './post-container/post-container.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { LikeContainerComponent } from './like-container/like-container.component';
import { CommentComponent } from './comment/comment.component';
import { CommentsToggleComponent } from './comments-toggle/comments-toggle.component';
import { PostContainerFeedComponent } from './post-container-feed/post-container-feed.component';
import { PostFormComponent } from './post-form/post-form.component';
import { UserContentInputFormComponent } from './user-content-input-form/user-content-input-form.component';
import { AccountRegisterContainerComponent } from './account-register-container/account-register-container.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { SearchNetworkComponent } from './search-network/search-network.component';
import { ProfileInformationComponent } from './profile-information/profile-information.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { FocusedUserPostsComponent } from './focused-user-posts/focused-user-posts.component';


@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    CommentContainerComponent,
    LinkContainerComponent,
    PostContainerComponent,
    CommentFormComponent,
    LikeContainerComponent,
    CommentComponent,
    CommentsToggleComponent,
    PostContainerFeedComponent,
    PostFormComponent,
    UserContentInputFormComponent,
    AccountRegisterContainerComponent,
    LoginPageComponent,
    HomePageComponent,
    UpdateProfileComponent,
    SearchNetworkComponent,
    ProfileInformationComponent,
    PasswordResetComponent,
    AboutUsComponent,
    FocusedUserPostsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
