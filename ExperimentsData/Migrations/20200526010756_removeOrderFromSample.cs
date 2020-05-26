using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace ExperimentsData.Migrations
{
    public partial class removeOrderFromSample : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Attributes_Samples_SampleEntityId",
                table: "Attributes");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_order",
                table: "Samples");

            migrationBuilder.DropIndex(
                name: "IX_Attributes_SampleEntityId",
                table: "Attributes");

            migrationBuilder.DropColumn(
                name: "order",
                table: "Samples");

            migrationBuilder.AlterColumn<string>(
                name: "name",
                table: "Datasets",
                type: "TEXT",
                nullable: false,
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AlterColumn<Guid>(
                name: "SampleEntityId",
                table: "Attributes",
                type: "TEXT",
                nullable: false,
                oldClrType: typeof(Guid),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AddUniqueConstraint(
                name: "AK_Datasets_name",
                table: "Datasets",
                column: "name");

            migrationBuilder.AddUniqueConstraint(
                name: "AK_Attributes_SampleEntityId_order",
                table: "Attributes",
                columns: new[] { "SampleEntityId", "order" });

            migrationBuilder.AddForeignKey(
                name: "FK_Attributes_Samples_SampleEntityId",
                table: "Attributes",
                column: "SampleEntityId",
                principalTable: "Samples",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Attributes_Samples_SampleEntityId",
                table: "Attributes");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_Datasets_name",
                table: "Datasets");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_Attributes_SampleEntityId_order",
                table: "Attributes");

            migrationBuilder.AddColumn<long>(
                name: "order",
                table: "Samples",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AlterColumn<string>(
                name: "name",
                table: "Datasets",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "TEXT");

            migrationBuilder.AlterColumn<Guid>(
                name: "SampleEntityId",
                table: "Attributes",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(Guid),
                oldType: "TEXT");

            migrationBuilder.AddUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_order",
                table: "Samples",
                columns: new[] { "DatasetEntityId", "order" });

            migrationBuilder.CreateIndex(
                name: "IX_Attributes_SampleEntityId",
                table: "Attributes",
                column: "SampleEntityId");

            migrationBuilder.AddForeignKey(
                name: "FK_Attributes_Samples_SampleEntityId",
                table: "Attributes",
                column: "SampleEntityId",
                principalTable: "Samples",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
